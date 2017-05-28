package com.szc.users.dao.Impl;

import com.szc.users.dao.UserManageCommonDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/28.
 */
@Repository("UserManageCommonDao")
public class UserManageCommonDaoImpl implements UserManageCommonDao {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    public Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public String SearchGroup(String searchgroups) {
        return null;
    }
}
