package com.xdbigdata.app_center.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.xdbigdata.mybatis.dto.CommonResult;
import com.xdbigdata.mybatis.exception.XDException;
import com.xdbigdata.user_manage.dto.RoleAndInfoDto;

/**
 * Created by xdpc111 on 2018/1/5.
 */
@Component
public class RemoteServiceResult {

    @Autowired
    private IRemoteService remoteService;

    @Autowired
    private Gson gson;

    /**
     * 根据sn获取用户用户角色及姓名工号信息
     * @param sn
     * @return
     */
    public RoleAndInfoDto findStudentInfoBySn(String sn) {
        RoleAndInfoDto roleAndInfoDto = null;
        try {
            CommonResult result = remoteService.findRoleAndInfoBySn(sn);
            if (result.isStatus() && result.getData() != null) {
                String roleAndInfoDtoJson = gson.toJson(result.getData());
                try {
                    roleAndInfoDto = gson.fromJson(roleAndInfoDtoJson, RoleAndInfoDto.class);
                } catch (ClassCastException e) {
                    throw new XDException("用户管理获取学生信息类型转换异常");
                }
            } else {
                throw new XDException("用户管理获取学生信息出错!");
            }
        } catch (Exception e) {
            throw new XDException("用户管理系统异常，请联系管理员");
        }

        return roleAndInfoDto;
    }
}
