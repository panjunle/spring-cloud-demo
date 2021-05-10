package com.seaway.springcloud.domo.dao;


import com.seaway.springcloud.domo.servera.SpDomains;

/**
 * TbSpDomains表DAO
 */
public interface ITbSpDomainsDAO {

    SpDomains getById(Long id);

    int update(SpDomains domains);
}
