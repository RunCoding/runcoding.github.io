package com.runcoding.pojo.po.account;

import java.io.Serializable;

/**
 * @desc 账号
 */
public class Account implements Serializable {

    private Long id;

    /**用户名*/
    private String username;

    /**记录创建时间*/
    private Integer createdTime;

    /**记录修改时间*/
    private Integer updatedTime;

    /**是否删除*/
    private Boolean isDiscarded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Integer updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDiscarded() {
        return isDiscarded;
    }

    public void setDiscarded(Boolean discarded) {
        isDiscarded = discarded;
    }
}
