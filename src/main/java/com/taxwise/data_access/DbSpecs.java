package com.taxwise.data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbSpecs {
    final static String url;
    final static String user;
    final static String password;
    static {
        Properties props = new Properties();
        try (InputStream inputStream = DbSpecs.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(inputStream);
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
