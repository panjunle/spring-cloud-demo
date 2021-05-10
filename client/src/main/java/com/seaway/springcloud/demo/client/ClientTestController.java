package com.seaway.springcloud.demo.client;

import com.seaway.springcloud.demo.SpDomains;
import com.seaway.springcloud.demo.dao.ITbSpDomainsDAO;
import com.seaway.springcloud.demo.testcommon.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

@RestController
@RefreshScope
public class ClientTestController {

    @Autowired
    private TestClient testClient;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    private ITbSpDomainsDAO domainsDAO;
    @Autowired
    private SpDomainsService domainsService;

    private static Logger logger = LoggerFactory.getLogger(ClientTestController.class);

    @Value("${foo}")
    private String bar;

    @GetMapping(value = "/get")
    public String getTest() {
        List<String> services = discoveryClient.getServices();
        for (String string : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(string);
            logger.info("服务名称：{},服务数量：{}", string, instances.size());
            for (ServiceInstance serviceInstance : instances) {
                Map<String, String> metadata = serviceInstance.getMetadata();
                for (Map.Entry<String, String> entry : metadata.entrySet()) {
                    logger.info(entry.getKey() + "|" + entry.getValue());
                }
            }
        }
        logger.info("准备向servera发起get请求");
        String xyzz = testClient.get("123", "xyzz");
        logger.info("servera返回:{}", xyzz);
        logger.info("获取servera的ip");
        return xyzz;
    }

    @PostMapping(value = "/post")
    public String postTest() {
        Map<String, String> map = new HashMap<>(3);
        map.put("test", "1.0.0.0");
        map.put("a", "213");
        map.put("b", "xyzz");
        List<String> services = discoveryClient.getServices();
        for (String string : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(string);
            System.out.println("服务名称：" + string + ",服务数量：" + instances.size());
            for (ServiceInstance instance : instances) {
                Map<String, String> metaMap = instance.getMetadata();
                for (Map.Entry<String, String> entry : metaMap.entrySet()) {
                    System.out.println(entry.getKey() + "|" + entry.getValue());
                }
            }
        }
        return testClient.post(map);
    }


    @GetMapping(value = "/discoveryClient")
    public String countService() {
        List<String> services = discoveryClient.getServices();
        for (String string : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(string);
            System.out.println("服务名称：" + string + ",服务数量：" + instances.size());
        }
        return "success";
    }

    @GetMapping("/cf")
    public String testConfig(@Value("${foo}") String foo) {
        System.out.println(foo);
        return foo;
    }

    @GetMapping("/cf1")
    public String testConfig1() {
        System.out.println(bar);
        return bar;
    }


    @GetMapping("/cf2")
    public String cf2Test() {
        try {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String footest = properties.getProperty("footest");
            if (StringUtils.hasText(footest)) {
                return footest;
            } else {
                return "blank";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }

    }

    @GetMapping("/stu")
    public Student sfefe() {
        System.out.println("client student");
        Student ss = new Student();
        ss.setName("clienta");
        ss.setAge(30);
        Student student = testClient.studentTest(ss);
        return student;
    }

    @GetMapping("/stuList")
    public String stuList() {
        Student ss = new Student();
        ss.setName("clienta");
        ss.setAge(30);

        Student s1 = new Student();
        s1.setName("panjl");
        s1.setAge(18);

        List<Student> ll = new ArrayList<>(2);
        ll.add(ss);
        ll.add(s1);

        String s = testClient.stuTest(ll);
        System.out.println(s);
        return "ok";
    }

    @PostMapping("/addDomains")
    public String addDomains() {
        SpDomains domains = new SpDomains();
        domains.setDomainName("clientadd" + System.currentTimeMillis());
        domains.setDomainSts(0);
        domains.setCreateTime(new Date());
        int insert = domainsDAO.insert(domains);
        return String.valueOf(insert);
    }

    @PostMapping("/txlcn")
    public String txlcnTest(HttpServletRequest request) {
        String tx = request.getParameter("tx");
        if (!StringUtils.hasText(tx) || "0".equals(tx)) {
            System.out.println("tx all ok");
        } else if ("1".equals(tx)) {
            System.out.println("clienta tx error");
        } else if ("2".equals(tx)) {
            System.out.println("servera tx error");
        } else {
            System.out.println("tx all ok");
        }
        System.out.println("enter clienta txlcn");
        try {
            int i = domainsService.txlcnTest(tx);
            System.out.println("clienta txlcn resutl:" + i);
        } catch (Exception e) {
            System.out.println("clienta txlcn exception");
        }
        return "clienta txlcn ok";
    }
}


