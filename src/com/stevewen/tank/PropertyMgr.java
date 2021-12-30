package com.stevewen.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String str){
        if(props== null) return null;
        return props.get(str);
    }

}
