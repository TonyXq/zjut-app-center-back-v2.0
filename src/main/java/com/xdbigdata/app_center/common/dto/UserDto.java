package com.xdbigdata.app_center.common.dto;

import java.io.Serializable;


public class UserDto implements Serializable {
    private Long id;

    private String username;

    private String password;

    private Integer zizhuType;

    private Integer welcomeType;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getZizhuType() {
        return zizhuType;
    }

    public void setZizhuType(Integer zizhuType) {
        this.zizhuType = zizhuType;
    }

    public Integer getWelcomeType() {
        return welcomeType;
    }

    public void setWelcomeType(Integer welcomeType) {
        this.welcomeType = welcomeType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}