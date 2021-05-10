package com.seaway.springcloud.domo.servera;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.seaway.springcloud.domo.dao.ITbSpDomainsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class SpDomainsServiceImpl implements SpDomainsService {

    @Autowired
    private ITbSpDomainsDAO domainsDAO;

    @Override
    @Transactional
    @LcnTransaction
    public int testLcn(String tx) {
        SpDomains domains = new SpDomains();
        domains.setDomainId(1L);
        domains.setDomainName("modify666");
        domains.setDomainSts(2);
        domains.setCreateTime(new Date());
        domains.setModifyTime(new Date());
        int update = domainsDAO.update(domains);
        domainsDAO.update(domains);
        System.out.println("servera update domains:" + update);
        if (!StringUtils.hasText(tx) || "0".equals(tx)) {
            System.out.println("tx all ok");
        } else if ("1".equals(tx)) {
            System.out.println("clienta tx error");
        } else if ("2".equals(tx)) {
            System.out.println("servera tx error");
            throw new IllegalStateException("servera tx error");
        } else {
            System.out.println("tx all ok");
        }
        return 1;
    }
}
