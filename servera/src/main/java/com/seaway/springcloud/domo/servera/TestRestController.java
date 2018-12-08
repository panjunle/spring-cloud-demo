package com.seaway.springcloud.domo.servera;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
public class TestRestController {

//    @Autowired
//    private ITbSpDomainsDAO domainsDAO;

    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    public Object getTest(HttpServletRequest request) {
        System.out.println("收到get test 请求");
        Map<String, String[]> parameterMap = request.getParameterMap();
        return parameterMap;
    }


    @RequestMapping(value = "/postTest", method = RequestMethod.POST)
    public Object postTest(@RequestBody Map<String, String> parameters) {
        System.out.println("收到post test 请求");
        return parameters;
    }

    @GetMapping(value = "/hy")
    public Object hyTest() {
        System.out.println("hy等待开始");
        try {
            TimeUnit.SECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hy等待结束");
        return "hy success.";
    }


    @GetMapping("/ip")
    public String getIp() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return "ip:" + localHost.getHostAddress();
        } catch (UnknownHostException e) {
            return "ip获取失败";
        }
    }

//    @RequestMapping(value = "/login")
//    public Object loginTesty(HttpServletRequest request) {
//        String domainId = request.getParameter("domainId");
//        if (domainId == null || domainId.length() == 0) {
//            return "domainId is null";
//        }
//        SpDomains domains = domainsDAO.getById(Long.valueOf(domainId));
//        if (domains != null) {
//            System.out.println(domains.toString());
//            return "login success";
//        } else {
//            return "login failed,domainId not exist!";
//        }
//    }

    @GetMapping("/hhh")
    public String setHealth(@RequestParam("health") boolean health) {
        ServeraConstant.HEALTH = health;
        return String.valueOf(health);
    }

}
