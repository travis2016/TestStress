package com.szc.users.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import com.szc.users.dao.Impl.TreeDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szc.users.beans.UserBean;
import com.szc.users.dao.Impl.UserDaoImpl;
import com.szc.users.service.UserService;

/**
 * 注册用户
 * @author TravisSong
 *
 */

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDaoImpl usersdao;
	@Resource
	private TreeDaoImpl treedao;
	
	@Override
	public UserBean save(UserBean user) {
			
		return usersdao.addUser(user);
	}
	
	public boolean isExitUser(String userName) {
		boolean juderesult = usersdao.isExitByName(userName);
		return juderesult;
	}
	
	@Override
	public boolean userLogin(String userName, String Password) {
		return usersdao.selectUser(userName, Password);
	}
	
	@Override
	public String searchUserNickname(String userName) {	
		return usersdao.SearchNickname(userName);
	}
	
	public UserDaoImpl getUsersdao() {
		return usersdao;
	}
	public void setUsersdao(UserDaoImpl usersdao) {
		this.usersdao = usersdao;
	}

	public TreeDaoImpl getTreedao() {return treedao;}
	public void setTreedao(TreeDaoImpl treedao) {this.treedao = treedao;}

	@Override
	public List searchUser() {
		return usersdao.selectUser();
	}

	@Override
	public List<?> searchListTree(String username) {
		return treedao.selectTreeList(username);
	}
}
