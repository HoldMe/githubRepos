package com.netty.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcUtil {
	
	public static JdbcTemplate init(){
		String url = "jdbc:mysql://localhost/localdb?characterEncoding=utf8&useSSL=true";
		String driverName = "com.mysql.jdbc.Driver";
		String userName = "com.mysql.jdbc.Driver";
		String passWd = "com.mysql.jdbc.Driver";
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWd);
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        return jdbcTemplate;
	}
	
    

}
