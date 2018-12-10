package com.xdbigdata.app_center.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InforMation {

    @ApiModelProperty("是否已经修改信息表示，1-跳转（未修改），0-已修改，不跳转")
    private String isUpdate;

    @ApiModelProperty("跳转地址")
    private String url;
}
