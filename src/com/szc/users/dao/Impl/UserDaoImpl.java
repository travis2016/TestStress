package com.szc.users.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.szc.users.beans.UserBean;
import com.szc.users.dao.UserDao;

@Repository("usersdao")
public class UserDaoImpl implements UserDao{
	
	@Resource(name="sessionFactory")
	 private  SessionFactory sessionFactory;
	 
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
	/**
	 *  保存用户 
	 *  @param user 
     *  @return boolean 
	 */
	public UserBean addUser(UserBean user) {
		try{
            Session sess = getSession();
            Transaction tx = sess.beginTransaction();
//            System.out.println("nickname"+user.getNickname());
            sess.save(user);
            tx.commit();          
            sess.close();         
            return user;
	    }catch(Exception e){
	            e.printStackTrace();
	            return null;
	    	}
	}

	@Override
	/**
	 * 判断用户是否存在
	 */
	public boolean isExitByName(String userName) {
		System.out.println("执行");
		Session sess = getSession();
//        Transaction tx = sess.beginTransaction();
        String sql="select * from userinfo where userName=?";
        SQLQuery query = sess.createSQLQuery(sql);
        query.setParameter(0,userName);
        List user = query.list();  
        sess.close();
        if(user.size()>0){  
            try {  
                return true;  
            } catch (Exception e) {
                 e.printStackTrace();  
                 
            }        
        }else{
        	return false;
        }
        return false;
	}

	@Override
	/**
	 * 判断用户是否登录成功
	 */
	public boolean selectUser(String userName, String Password) {
		Session sess = getSession();
//        Transaction tx = sess.beginTransaction();
        String sql="select * from userinfo where userName=? and password = ?";
        SQLQuery query = sess.createSQLQuery(sql);
        query.setParameter(0,userName);
        query.setParameter(1,Password);
        List user = query.list(); 
        sess.close();
        if(user.size()>0){  
            try {  
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
             
        }else{  
	        return false; 
        }
        return false;
	}
	
	@Override
	/**
	 * 查询用户列表
	 */
	public List selectUser(){
		Session sess = getSession();
		String sql="select userName,gender,nickname,groupid from userinfo";
		SQLQuery query = sess.createSQLQuery(sql);
        List userlist = query.list(); 
        sess.close();
        return userlist;
	}
	
	public String SearchNickname(String userName){
		Session sess = getSession();
        Transaction tx = sess.beginTransaction();
        String sql="select nickname from userinfo where userName=?";
        SQLQuery query = sess.createSQLQuery(sql);
        query.setParameter(0,userName);
        List user = query.list(); 
        String nickname="";
        if(user.size()>0){  
            try {  
                tx.commit(); 
                nickname = user.get(0).toString();
                sess.close(); 
            } catch (Exception e) {  
                e.printStackTrace();  
            }               
        }    
		return nickname;
	}
	
}
