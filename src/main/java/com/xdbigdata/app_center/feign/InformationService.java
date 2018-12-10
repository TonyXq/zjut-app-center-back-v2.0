package com.xdbigdata.app_center.feign;


import com.xdbigdata.mybatis.dto.CommonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "zjut-welcome")
public interface InformationService {

    /**
     * 根据学生学号和表的类型名查看是否该类型信息管理学生已经修改
     * @param sn
     * @param type
     * @return
     */
    @RequestMapping(value = "/information/findResultBySn/{sn}/{type}",method = RequestMethod.GET)
    CommonResult findResultBySn(@PathVariable("sn") String sn, @PathVariable("type") Integer type);
}
