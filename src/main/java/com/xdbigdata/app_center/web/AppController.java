package com.xdbigdata.app_center.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdbigdata.app_center.domain.App;
import com.xdbigdata.app_center.mapper.AppMapper;
import com.xdbigdata.mybatis.dto.CommonResult;
import com.xdbigdata.mybatis.exception.ErrorCode;
import com.xdbigdata.user_manage.domain.Role;
import com.xdbigdata.user_manage.dto.RoleAndInfoDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by xdpc111 on 2017/9/6.
 */
@Api("应用相关")
@RequestMapping("/product")
@RestController
public class AppController {

   @Autowired
   private AppMapper appMapper;


    @ApiOperation(value = "查看所有应用")
    @RequestMapping(value = "/api/findAll",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult findAll(HttpSession httpSession,@RequestBody RoleAndInfoDto roleAndInfoDto){
        List<App> appList = null;
        RoleAndInfoDto roleAndInfoDto1 = null;
        if (roleAndInfoDto == null){
            roleAndInfoDto1 = (RoleAndInfoDto) httpSession.getAttribute("sessionUser");
        }else {
            roleAndInfoDto1 = roleAndInfoDto;
            httpSession.setAttribute("sessionUser",roleAndInfoDto1);
        }
        List<Role> roleList = roleAndInfoDto1.getRoleList();
        if (roleList != null && roleList.size()>0){
            List<String> roleName = new ArrayList<>();
            for (Role role:roleList){
                roleName.add(role.getName());
            }
            appList = appMapper.findByRole(roleName);
        }
        return new CommonResult(true, appList, ErrorCode.FIND_SUCCESS.code, ErrorCode.FIND_SUCCESS.des);
    }
}
