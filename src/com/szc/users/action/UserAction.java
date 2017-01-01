package com.szc.users.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Failure;
import com.szc.users.beans.UserBean;
import com.szc.users.service.UserService;
import com.szc.users.service.Impl.UserServiceImpl;

/**
 * 
 * @author TravisSong
 * UserAction负责处理用户表的一些操作
 */
//@Controller("UserAction")
@Scope("prototype")
@Namespace("/")
@ParentPackage(value="test")
public class UserAction  extends ActionSupport {
    
	private static final long serialVersionUID = 1L;  
    private HttpServletRequest request;
  	private HttpServletResponse response;
  	private ServletContext Context;
  	private WebApplicationContext ctx;
  	private PrintWriter out;
	private String username;
  	
  	private UserServiceImpl userService; 
  	
  	 @Autowired
 	public void setUserService(UserServiceImpl userService) {
 		this.userService = userService;
 	}
     
     public UserServiceImpl getUserService() {
 		return userService;
 	}
    
  	public UserAction() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");//设置传输编码
		Context = ServletActionContext.getServletContext();
		ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(Context);
//		response.setContentType("text/json");
  	}
  	
  	
	//查询用户的数据
	@Action(value = "/selectAction"
			,results = { @Result(name = "add", location = "/login.jsp")}
			,interceptorRefs= {@InterceptorRef(value="checkLoginStack")}) 	
	public void selectUser() {
		try {
			out= response.getWriter();
			UserBean selectUser=new UserBean();
	    	List userList=userService.searchUser();
	    	/*
	    	Object[] user1=(Object[])userList.get(0);
	    	JSONArray selectResult = JSONArray.fromObject(userList);
	    	int userid=(Integer)user1[0];
	    	String userName=(String)user1[1];
	    	System.out.println(userid);
	    	System.out.println(userName);
	    	System.out.println(selectResult.toString());
	    	*/
	    	JSONArray selectResult = JSONArray.fromObject(userList);
	    	JSONArray dataJsonArray = new JSONArray();
	    	JSONObject resultJson = new JSONObject();
	    	resultJson.element("status", "1");
	    	resultJson.element("error_code", "0");
	    	for(int i=0;i<selectResult.size();i++){
	    		Object[] user1=(Object[])userList.get(i);
	    		JSONObject dataJsonObject = new JSONObject();
	    		dataJsonObject.element("username", (String)user1[0]);
	    		dataJsonObject.element("sex", (Integer)user1[1]);
	    		dataJsonObject.element("usernickname", (String)user1[2]);
	    		dataJsonObject.element("group", (Integer)user1[3]);
	    		dataJsonArray.add(i, dataJsonObject);
	    	}
	    	resultJson.element("data", dataJsonArray);
	    	LOG.info("返回json数据了============Test111");
	    	out.print(resultJson);
	    	out.flush();
	    	out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//查询用户拥有的权限
	@Action(value = "/selectTreeAction")
	public void  SelectTreeList(){
		try {
			out = response.getWriter();
			String selectusername = request.getParameter("username");
			List selectTreeList=userService.searchListTree(selectusername);
			for(int i=0;i<selectTreeList.size();i++){
				Object[] treeList=(Object[])selectTreeList.get(i);
				System.out.println(treeList[0]);
			}
			out.print(selectTreeList);
			out.flush();
			out.close();
		}catch (Exception e){

		}


	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
