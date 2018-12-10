package com.xdbigdata.app_center.common.jwt;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tangyijun on 2017/3/27.
 * good good study,day day up!
 */
@Data
public class AccessToken implements Serializable{
    private String access_token;
    private String token_type;
    private long expires_in;
}
