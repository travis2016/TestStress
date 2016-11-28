package com.szc.users.dao.Impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDaoImpl<T> {

	@Resource(name = "sessionFactory")  
    protected SessionFactory sessionFactory;
	
	public Session getSession() {
        return sessionFactory.getCurrentSession();  
	}

}
