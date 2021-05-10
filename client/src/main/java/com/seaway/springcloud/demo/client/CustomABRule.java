//package com.seaway.springcloud.demo.client;
//
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.AbstractLoadBalancerRule;
//import com.netflix.loadbalancer.Server;
//import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.springframework.web.servlet.support.RequestContext;
//import org.springframework.web.servlet.support.RequestContextUtils;
//import org.springframework.web.util.WebUtils;
//
//import javax.servlet.ServletContext;
//import java.util.List;
//import java.util.Map;
//
//
//public class CustomABRule extends AbstractLoadBalancerRule {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    private String clientName;
//
//    @Override
//    public void initWithNiwsConfig(IClientConfig iClientConfig) {
//        this.clientName = iClientConfig.getClientName();
//    }
//
//    @Override
//    public Server choose(Object key) {
//        if (null != key) {
//            logger.info("choose key:{}", key);
//        }
//        logger.info("server-name:{}", clientName);
//        List<Server> reachableServers = getLoadBalancer().getAllServers();
//        if (reachableServers.size() <= 0) {
//            return null;
//        }
//
//        Server server = reachableServers.get(0);
//        if (server.isAlive() && server.isReadyToServe()) {
//            if (server instanceof DiscoveryEnabledServer) {
//                for (Map.Entry<String, String> entry : ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata().entrySet()) {
//                    logger.info("metadata:{}-{}", entry.getKey(), entry.getValue());
//                }
//            }
//            return server;
//        }
//
//        return null;
//    }
//
//}
