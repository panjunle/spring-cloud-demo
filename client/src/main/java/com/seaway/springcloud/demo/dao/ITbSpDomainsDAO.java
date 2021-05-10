package com.seaway.springcloud.demo.dao;


import com.seaway.springcloud.demo.SpDomains;

/**
 * TbSpDomains表DAO
 */
public interface ITbSpDomainsDAO {

    SpDomains getById(Long id);

    int update(SpDomains domains);

    int insert(SpDomains domains);

}
