package com.xdbigdata.app_center.util.common;


import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class YMLUtil {

    private static String profilepath = "/application-test.yml";
    private static Map map = new HashMap();

    public static Object getKeyValue(String key){
        return map.get(key);
    }

    static {
        try {
            Yaml yaml = new Yaml();
            InputStream resourceAsStream = YMLUtil.class.getResourceAsStream(profilepath);
            map= (HashMap<String,Map>)yaml.load(resourceAsStream);
        }  catch (Exception var2) {
            var2.printStackTrace();
        }
    }
}
