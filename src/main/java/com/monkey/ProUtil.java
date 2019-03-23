package com.monkey;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProUtil {
    private Properties prop;
    private String filePath;

    //构造方法
    public ProUtil(String string) {
        this.filePath = string;
        this.prop = readProperties();
    }

    //读取key值
    private Properties readProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            BufferedInputStream in = new BufferedInputStream(inputStream);
            properties.load(in);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    //获取配置文件
    public String getPro(String key) {
        if(prop.containsKey(key)) {
            String name = prop.getProperty(key);
            return name;
        }else {
            System.out.println("没有获取到"+key+"值");
            return "";
        }

    }
}
