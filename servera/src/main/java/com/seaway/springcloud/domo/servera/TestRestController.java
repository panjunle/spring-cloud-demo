package com.seaway.springcloud.domo.servera;

import com.seaway.springcloud.demo.testcommon.Student;
import com.seaway.springcloud.domo.dao.ITbSpDomainsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
public class TestRestController {

    @Autowired
    private ITbSpDomainsDAO domainsDAO;

    @Autowired
    private SpDomainsService domainsService;


    private static final Logger LOGGER = LoggerFactory.getLogger(TestRestController.class);

    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    public Object getTest(HttpServletRequest request) {
        LOGGER.info("收到get test 请求");
        Map<String, String[]> parameterMap = request.getParameterMap();
        LOGGER.info("请求参数:{}", parameterMap);
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
            LOGGER.info("查询本机ip");
            InetAddress localHost = InetAddress.getLocalHost();
            return "ip:" + localHost.getHostAddress();
        } catch (UnknownHostException e) {
            return "ip获取失败";
        }
    }

    @RequestMapping(value = "/login")
    public Object loginTesty(HttpServletRequest request) {
        String domainId = request.getParameter("domainId");
        if (domainId == null || domainId.length() == 0) {
            return "domainId is null";
        }
        SpDomains domains = domainsDAO.getById(Long.valueOf(domainId));
        if (domains != null) {
            System.out.println(domains.toString());
            return "login success";
        } else {
            return "login failed,domainId not exist!";
        }
    }

    @RequestMapping(value = "/logout")
    public Object loginOutTesty(HttpServletRequest request) {
        String domainId = request.getParameter("domainId");
        if (domainId == null || domainId.length() == 0) {
            return "domainId is null";
        }
        SpDomains domains = domainsDAO.getById(Long.valueOf(domainId));
        if (domains == null) {
            return "login failed,domainId not exist!";
        }
        domains.setDomainName("txlcn" + System.currentTimeMillis());
        int update = domainsDAO.update(domains);
        System.out.println("update resutl:" + update);
        return update;
    }

    @GetMapping("/hhh")
    public String setHealth(@RequestParam("health") boolean health) {
        ServeraConstant.HEALTH = health;
        return String.valueOf(health);
    }

    @PostMapping("/stu")
    public Student stuTest(@RequestBody Student stu) {
        System.out.println("servera student");
        if (null == stu) {
            stu = new Student();
            stu.setName("null");
        }
        stu.setName(stu.getName() + "|add by server a");
        return stu;
    }


    @PostMapping("/stuList")
    public String stuTest(@RequestBody List<Student> stuList) {
        if (stuList == null || stuList.size() == 0) {
            System.out.println("null");
            return "null";
        } else {
            for (Student student : stuList) {
                System.out.println(student.getName() + ":" + student.getAge());
            }
            return "list ok";
        }
    }

    @PostMapping("/txlcn")
    public String txlcnTest(HttpServletRequest request) {
        String tx = request.getParameter("tx");
        System.out.println("enter servera txlcn");
//        try {
        int i = domainsService.testLcn(tx);
        System.out.println("servera txlcn resutl:" + i);
//        }catch (Exception e){
//            System.out.println("servera txlcn excetpion");
//        }
        return "server a txlcn ok";
    }
}
