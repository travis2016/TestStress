package com.szc.users.dao.Impl;


import com.szc.users.dao.TreeDao;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :travissong
 */
@Repository("treedao")
public class TreeDaoImpl implements TreeDao {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public List selectTreeList(String username) {
        Session sess = getSession();
        String sql = "SELECT * FROM treeurl WHERE FIND_IN_SET(treeid,(SELECT a.treeidlist FROM rolegroup a WHERE groupid = (SELECT userinfo.groupid FROM userinfo" +
                " WHERE userinfo.userName =?))) AND isshow = 1 ORDER BY treeid";
        SQLQuery query = sess.createSQLQuery(sql);
        query.setParameter(0,username);
        List treelist = query.list();
        sess.close();
        return treelist;
    }
}
