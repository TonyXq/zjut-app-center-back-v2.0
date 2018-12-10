package com.xdbigdata.app_center.feign;

import com.xdbigdata.mybatis.dto.CommonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xdpc111 on 2018/1/5.
 */
@FeignClient(name = "user-center")
//@FeignClient(name = "user-center-test",url = "192.168.2.68:8086")
public interface IRemoteService {

    /**
     * 根据sn获取用户用户角色及姓名工号信息
     * @param sn
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user/api/findRoleAndInfoBySn/{sn}")
    CommonResult findRoleAndInfoBySn(@PathVariable("sn") String sn);

    /**
     * 查询学生是否修改信息管理
     * @param sn
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/studentCompletion/findStudentChangeInfo/{sn}/{type}", method = RequestMethod.GET)
    CommonResult findStudentChangeInfo(@PathVariable("sn") String sn, @PathVariable("type") Integer  type);
}
