package com.seaway.springcloud.domo.servera;

import java.io.Serializable;
import java.util.StringJoiner;


/**
 * TB_SP_DOMAINS
 */
public class SpDomains implements Serializable {

    private static final long serialVersionUID = -20180517102902388L;

    //columns START

    /**
     * 渠道ID, 手动分配
     */
    private Long domainId;

    /**
     * 渠道名称
     */
    private String domainName;

    /**
     * 渠道联系人
     */
    private String domainContacts;

    /**
     * 渠道联系号码
     */
    private String domainTel;

    /**
     * 渠道IP，多个用逗号(,)分隔
     */
    private String domainIps;

    /**
     * 10：正常，-10：锁定
     */
    private Integer domainSts;

    /**
     * 默认向渠道推送通知的接口地址
     */
    private String callbackUrl;

    /**
     * 创建时间
     */
    private java.util.Date createTime;

    /**
     * 修改时间
     */
    private java.util.Date modifyTime;

    /**
     * 渠道说明
     */
    private String domainDesc;
    //columns END

    public SpDomains() {
    }

    public SpDomains(
            Long domainId
    ) {
        this.domainId = domainId;
    }


    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getDomainId() {
        return this.domainId;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainContacts(String domainContacts) {
        this.domainContacts = domainContacts;
    }

    public String getDomainContacts() {
        return this.domainContacts;
    }

    public void setDomainTel(String domainTel) {
        this.domainTel = domainTel;
    }

    public String getDomainTel() {
        return this.domainTel;
    }

    public void setDomainIps(String domainIps) {
        this.domainIps = domainIps;
    }

    public String getDomainIps() {
        return this.domainIps;
    }

    public void setDomainSts(Integer domainSts) {
        this.domainSts = domainSts;
    }

    public Integer getDomainSts() {
        return this.domainSts;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setModifyTime(java.util.Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public java.util.Date getModifyTime() {
        return this.modifyTime;
    }

    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc;
    }

    public String getDomainDesc() {
        return this.domainDesc;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SpDomains.class.getSimpleName() + "[", "]")
                .add("domainId=" + domainId)
                .add("domainName='" + domainName + "'")
                .add("domainContacts='" + domainContacts + "'")
                .add("domainTel='" + domainTel + "'")
                .add("domainIps='" + domainIps + "'")
                .add("domainSts=" + domainSts)
                .add("callbackUrl='" + callbackUrl + "'")
                .add("createTime=" + createTime)
                .add("modifyTime=" + modifyTime)
                .add("domainDesc='" + domainDesc + "'")
                .toString();
    }
}

