package com.seaway.springcloud.domo.servera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthChecker implements HealthIndicator {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Health health() {
        logger.debug("开始自定义健康检查");
        ServeraConstant serveraConstant = new ServeraConstant();
        if (serveraConstant.getHp()) {
            logger.debug("健康检查成功");
            return new Health.Builder().withDetail("htitle", "im ok.im alive.").withDetail("HP", 100).up().build();
        } else {
            logger.debug("健康检查失败");
            return new Health.Builder().withDetail("error", "i am down").down().build();
        }

    }

}
