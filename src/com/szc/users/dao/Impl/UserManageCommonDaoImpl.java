package com.szc.users.dao.Impl;

import com.szc.users.dao.UserManageCommonDao;
import com.szc.util.Log4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.Log;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.*;
import java.util.logging.Logger;

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
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List SearchGroup(String searchgroups) {
        Session sess = getSession();
        String sql;
        Query query;
        if("".equals(searchgroups)){
            sql = "select * from rolegroup";
            query = sess.createSQLQuery(sql);
        }else{
             sql = "select * from rolegroup where groupname= :groupname";
            query = sess.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            query.setString("groupname",searchgroups);
        }
        List groupList = query.list();
        return groupList;
    }
}
