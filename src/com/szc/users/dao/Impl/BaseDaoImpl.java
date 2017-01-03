package com.szc.users.dao.Impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDaoImpl {

	@Resource(name = "sessionFactory")  
    protected SessionFactory sessionFactory;
	
	public Session getSession() {
        return sessionFactory.getCurrentSession();  
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://172.17.4.33:3306/sshproject",
				"root",
				"root");
		return conn;
	}
}
