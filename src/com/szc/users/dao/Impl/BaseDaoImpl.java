package com.szc.users.dao.Impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDaoImpl {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
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
