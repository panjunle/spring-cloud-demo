package com.seaway.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringcloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudGatewayApplication.class, args);
    }


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.host("**.changeuri.org").and().header("X-Next-Url")
                        .uri("http://blueskykong.com"))
                .route(r -> r.host("**.changeuri.org").and().query("url")
                        .uri("http://blueskykong.com"))
                .route(r -> r.path("/getTest").filters(f -> {
                    f.requestRateLimiter(config -> {
                        config.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                        config.setRateLimiter(redisRateLimiter());
                        config.setKeyResolver(remoteAddrKeyResolver());
                    });
                    f.addResponseHeader("GateWayMark", "add by gateway");
                    f.hystrix(hy-> hy.setName("serverahy").setFallbackUri("forward:/fallback"));
                    return f;
                })
                        .uri("lb://servera"))
                .build();
    }

    @Bean
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 2);
    }

    @Bean
    @Order(0)
    public GlobalFilter tokenFilter() {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String token = headers.getFirst("token");
            System.out.println("token:" + token);
            if(StringUtils.hasText(token)){
                return chain.filter(exchange);
            }else{
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        };
    }

}

