package com.xdbigdata.app_center.config;

import com.xdbigdata.app_center.util.common.YMLUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Map;
import java.util.Properties;

/**
 * Created by tangyijun on 2017/6/21.
 * good good study,day day up!
 */
@Configuration
public class MybatisConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        Map keyValue = (Map) YMLUtil.getKeyValue("my-config");
        String mapperPackage = (String)keyValue.get("mapper-package");
        String xdMapper= (String)keyValue.get("xd-mapper");
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage(mapperPackage);
        Properties p = new Properties();
        p.setProperty("mappers", xdMapper);
        msc.setProperties(p);
        return msc;
    }


}
