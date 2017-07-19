package com.szc.users.dao.Impl;


import com.szc.users.beans.TreeUrlBean;
import com.szc.users.dao.TreeDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :travissong
 */
@Repository("treedao")
public class TreeDaoImpl extends BaseDaoImpl implements TreeDao {
    @Resource(name="sessionFactory")
    public  SessionFactory sessionFactory;

    @Override
    public List selectTreeList(String username) {
        BaseDaoImpl baseSession = new BaseDaoImpl();
        baseSession.setSessionFactory(sessionFactory);
        Session sess = baseSession.getSession();
        String sql = "SELECT * FROM treeurl WHERE FIND_IN_SET(treeid,(SELECT a.treeidlist FROM rolegroup a WHERE groupid = (SELECT userinfo.groupid FROM userinfo" +
                " WHERE userinfo.userName =:username))) AND isshow = 1 ORDER BY treeid";
        Query query = sess.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setString("username",username);
        List treelist = query.list();
        sess.close();
        return treelist;
    }

    /**
     * 根据提供的listid，查找对应的名字
     * @param searchListId
     * @return
     */
    @Override
    public List selectTreeName(String searchListId){
        BaseDaoImpl baseSession = new BaseDaoImpl();
        String[] treeNameListArray = searchListId.split(",");
        baseSession.setSessionFactory(sessionFactory);
        Session sess = baseSession.getSession();
        String sql = "SELECT treename FROM treeurl AS a WHERE a.treeid IN(:searchListId)";
        Query query = sess.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameterList("searchListId",treeNameListArray);
        List treeNameList =query.list();
        sess.close();
        return treeNameList;
    }
}



/**
 public List<TreeUrlBean> selectTreeList(String username) {
 Connection conn = null;
 ArrayList<TreeUrlBean> resultTreeList = new ArrayList<TreeUrlBean>();
 try{
 conn = getConnection();
 StringBuffer sf = new StringBuffer("SELECT * FROM treeurl WHERE FIND_IN_SET(treeid,(SELECT a.treeidlist FROM rolegroup a WHERE groupid = (SELECT userinfo.groupid FROM userinfo" +
 "  WHERE userinfo.userName = :username))) AND isshow = 1 ORDER BY treeid");
 NamedParameterStatement psmt = new NamedParameterStatement(conn,sf.toString());
 if (!(username.equals(""))) {
 psmt.setString("username",username);
 }
 ResultSet rs = psmt.executeQuery();
 while(rs.next()){
 TreeUrlBean tempTreeList = new TreeUrlBean();
 tempTreeList.setTreeid(rs.getInt("treeid"));
 tempTreeList.setTreename(rs.getString("tempTreeList"));
 tempTreeList.setParentid(rs.getInt("parentid"));
 tempTreeList.setUrl(rs.getString("url"));
 tempTreeList.setIsshow(rs.getInt("isshow"));
 resultTreeList.add(tempTreeList);
 }
 }catch (Exception e){

 }
 return resultTreeList;
 }
 */
