package com.supcon.entity;

import java.util.Date;

/**
 * 用户信息表entity类
 */
public class OrderPlatformUser {
    private Integer id;

    private String userName;

    private String password;

    private String mobilePhone;

    private String email;

    private String activatedCode;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

    private String description;


    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getMobilePhone() {
      return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
      this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getActivatedCode() {
      return activatedCode;
    }

    public void setActivatedCode(String activatedCode) {
      this.activatedCode = activatedCode;
    }

    public Integer getStatus() {
      return status;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }

    public Date getCreateTime() {
      return createTime;
    }

    public void setCreateTime(Date createTime) {
      this.createTime = createTime;
    }

    public Date getModifyTime() {
      return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
      this.modifyTime = modifyTime;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
}
