package com.xdbigdata.app_center.web;

import com.xdbigdata.app_center.domain.App;
import com.xdbigdata.app_center.mapper.AppMapper;
import com.xdbigdata.mybatis.dto.CommonResult;
import com.xdbigdata.mybatis.exception.ErrorCode;
import com.xdbigdata.user_manage.domain.Role;
import com.xdbigdata.user_manage.dto.RoleAndInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/api/findAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult findAll(HttpSession httpSession, @RequestBody RoleAndInfoDto roleAndInfoDto) {
        RoleAndInfoDto roleAndInfoDtoInSession = (RoleAndInfoDto) httpSession.getAttribute("sessionUser");
        //根据前端传递的角色确定参数
        List<Role> roles = roleAndInfoDto.getRoleList();
        // 验证角色集合有没有(如果没有,说明修改了参数)
        // 验证选择的角色集合是否大于1(如果大于1,说明不是正常操作,修改了参数)
        // 验证用户的session中角色是否为null或者没有,如果没有,提示需要增加角色.
        // 验证选择的角色是否在session中的角色中(如果不在,说明改了角色参数)
        if (CollectionUtils.isEmpty(roles)) {
            return new CommonResult(false, null, ErrorCode.HANDLER_FAILED.code, "请选择角色");
        } else if (roles.size() > 1) {
            return new CommonResult(false, null, ErrorCode.HANDLER_FAILED.code, "角色只能选择一个");
        }else if (CollectionUtils.isEmpty(roleAndInfoDtoInSession.getRoleList())){
            return new CommonResult(false,null,ErrorCode.HANDLER_FAILED.code,"当前用户没有角色");
        }else if(!roleAndInfoDtoInSession.getRoleList().containsAll(roles)){
            return new CommonResult(false,null,ErrorCode.HANDLER_FAILED.code,"选择的角色不在当前用户角色信息中");
        }
        //把角色集合重新设值到session中
        ArrayList<Role> selectedRoles = new ArrayList<>();
        Role role = roles.get(0);
        selectedRoles.add(role);
        roleAndInfoDtoInSession.setRoleList(selectedRoles);
        httpSession.setAttribute("sessionUser", roleAndInfoDtoInSession);
        //根据设值的rolename,选择出需要展示的应用.
        List<String> roleName = new ArrayList<>();
        for (Role r : selectedRoles) {
            roleName.add(r.getName());
        }
        List<App> appList = appMapper.findByRole(roleName);
        return new CommonResult(true, appList, ErrorCode.FIND_SUCCESS.code, ErrorCode.FIND_SUCCESS.des);
    }
}
