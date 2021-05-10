package com.seaway.springcloud.demo.client;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.seaway.springcloud.demo.SpDomains;
import com.seaway.springcloud.demo.dao.ITbSpDomainsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class SpDomainsServiceImpl implements SpDomainsService {

    @Autowired
    private ITbSpDomainsDAO domainsDAO;
    @Autowired
    private TestClient testClient;

    @Override
    @Transactional
    @LcnTransaction
    public int txlcnTest(String tx) {
        SpDomains domains = new SpDomains();
        domains.setDomainName("clientadd" + System.currentTimeMillis());
        domains.setDomainSts(0);
        domains.setCreateTime(new Date());
        int insert = domainsDAO.insert(domains);
        System.out.println("clienta insert result:" + insert);
        System.out.println("start servera txlcn");
        String s = testClient.serveraTxlcn(tx);
        System.out.println("servera txlcn resutl:" + s);
        if (!StringUtils.hasText(tx) || "0".equals(tx)) {
            System.out.println("tx all ok");
        } else if ("1".equals(tx)) {
            System.out.println("clienta tx error");
            throw new IllegalStateException("clienta tx error");
        } else if ("2".equals(tx)) {
            System.out.println("servera tx error");
        } else {
            System.out.println("tx all ok");
        }
        return insert;
    }
}
