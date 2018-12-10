package com.xdbigdata.app_center.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xdbigdata.app_center.feign.InfomationServiceResult;
import com.xdbigdata.user_manage.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xdbigdata.app_center.domain.User;
import com.xdbigdata.app_center.feign.RemoteServiceResult;
import com.xdbigdata.app_center.service.IUserService;
import com.xdbigdata.mybatis.dto.CommonResult;
import com.xdbigdata.mybatis.exception.ErrorCode;
import com.xdbigdata.user_manage.dto.RoleAndInfoDto;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdpc111 on 2017/9/6.
 */
@Api("登录相关")
@RequestMapping("/user")
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private InfomationServiceResult infomationServiceResult;

    @Autowired
    private RemoteServiceResult remoteServiceResult;

    public static final String USER_IN_SESSION = "sessionUser";

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody User user, HttpSession httpSession, HttpServletResponse response) throws Exception {
        User query = new User();
        query.setUsername(user.getUsername());
        query.setPassword(user.getPassword());
        User u = userService.getByProperty(query);
        if (u != null) {
//            String baseStr = MD5KeyCons.USER_LOGIN_KEY + u.getPassword() + MD5KeyCons.USER_LOGIN_KEY;
//            String md5 = DigestUtils.md5DigestAsHex(baseStr.getBytes());
//            if (md5.equals(user.getPassword())) {
//                u.setPassword(null);
//                httpSession.setAttribute(USER_IN_SESSION, u);
//                String accessToken = JwtHelper.createJWT(JwtHelper.CLIENT_ID, new JSONObject(u).toString(), JwtHelper.EXPIRES_SECOND);
//                Cookie cookie = new Cookie("token", accessToken);
//                cookie.setMaxAge(30 * 60);
//                cookie.setPath("/");
//                response.addCookie(cookie);
//                return new CommonResult(true, u, ErrorCode.LOGIN_SUCCESS.code, ErrorCode.LOGIN_SUCCESS.des);
//            }
            RoleAndInfoDto roleAndInfoDto = remoteServiceResult.findStudentInfoBySn(u.getUsername());
            if (roleAndInfoDto != null){
                httpSession.setAttribute(USER_IN_SESSION,roleAndInfoDto);
                return new CommonResult(true, roleAndInfoDto, ErrorCode.LOGIN_SUCCESS.code, ErrorCode.LOGIN_SUCCESS.des);
            }else {
                return new CommonResult(false, null, ErrorCode.LOGIN_FAILED_USERNAME_OR_PASSWORD.code, ErrorCode.LOGIN_FAILED_USERNAME_OR_PASSWORD.des);
            }
        }else {
            return new CommonResult(false, null, ErrorCode.LOGIN_FAILED_USERNAME_OR_PASSWORD.code, ErrorCode.LOGIN_FAILED_USERNAME_OR_PASSWORD.des);
        }
    }

    @RequestMapping(value = "/api/out", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult out(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        httpSession.removeAttribute(USER_IN_SESSION);
        return new CommonResult(true, "/#/", ErrorCode.HANDLER_SUCCESS.code, ErrorCode.HANDLER_SUCCESS.des);
    }


    @RequestMapping(value = "/api/newLogin", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getSession(HttpSession httpSession, HttpServletRequest request) {
        RoleAndInfoDto roleAndInfoDto = (RoleAndInfoDto) httpSession.getAttribute("sessionUser");
//
//            RoleAndInfoDto userInSession = new RoleAndInfoDto();
//            userInSession.setName("罗峰");
//            userInSession.setSn("201502071213");
//            List<Role> roleList1 = new ArrayList<>();
//            Role role1 = new Role();
//            role1.setId(1L);
//            role1.setName("学生");
//            roleList1.add(role1);
//            userInSession.setRoleList(roleList1);
//            httpSession.setAttribute(USER_IN_SESSION,userInSession);
//

       if(roleAndInfoDto !=null){
           return new CommonResult(true, roleAndInfoDto, ErrorCode.HANDLER_SUCCESS.code, ErrorCode.HANDLER_SUCCESS.des);
       }else{
           return new CommonResult(false, null, ErrorCode.HANDLER_SUCCESS.code, ErrorCode.HANDLER_SUCCESS.des);
       }
    }

    @RequestMapping(value = "/api/information/{sn}/{userType}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getInformation(@PathVariable String sn, @PathVariable String userType){
        return new CommonResult(true, infomationServiceResult.getInfoMationReslut(sn,userType), ErrorCode.HANDLER_SUCCESS.code, ErrorCode.HANDLER_SUCCESS.des);
    }
}
