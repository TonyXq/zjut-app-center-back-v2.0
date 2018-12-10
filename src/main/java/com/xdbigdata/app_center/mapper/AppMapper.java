package com.xdbigdata.app_center.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xdbigdata.app_center.domain.App;
import com.xdbigdata.mybatis.Mapper.XDMapper;

public interface AppMapper extends XDMapper<App> {

    List<App> findByRole(@Param("roleName") List<String> roleName);

}