package com.szc.users.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.szc.users.service.Impl.UserCommonManagerServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.szc.users.beans.UserBean;
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
  	private UserCommonManagerServiceImpl groupServiceDao;
  	private UserServiceImpl userServiceDao;
  	
  	@Autowired
 	public void setUserService(UserServiceImpl userServiceDao) {
 		this.userServiceDao = userServiceDao;
 	}
	public UserServiceImpl getUserService() {
 		return userServiceDao;
 	}

	public UserCommonManagerServiceImpl getGroupServiceDao() {
		return groupServiceDao;
	}
	@Autowired
	public void setGroupServiceDao(UserCommonManagerServiceImpl groupServiceDao) {
		this.groupServiceDao = groupServiceDao;
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
			String selectParam = request.getParameter("search");
			if(selectParam.equals("searchUserList")) {
				List userList = userServiceDao.searchUser();
				JSONArray selectResult = JSONArray.fromObject(userList);
				JSONArray dataJsonArray = new JSONArray();
				JSONObject resultJson = new JSONObject();
				resultJson.element("status", "1");
				resultJson.element("error_code", "0");
				for (int i = 0; i < selectResult.size(); i++) {
					Object[] user1 = (Object[]) userList.get(i);
					JSONObject dataJsonObject = new JSONObject();
					dataJsonObject.element("username", (String) user1[0]);
					dataJsonObject.element("sex", (Integer) user1[1]);
					dataJsonObject.element("usernickname", (String) user1[2]);
					dataJsonObject.element("group", (String) user1[3]);
					dataJsonObject.element("userId", (Integer) user1[4]);
					dataJsonArray.add(i, dataJsonObject);
				}
				resultJson.element("data", dataJsonArray);
				System.out.print("res::"+resultJson);
				out.print(resultJson);
			}else if(selectParam.equals("searchGroupList")){
				List groupList = groupServiceDao.SearchGroup("");
				JSONArray selectResult = JSONArray.fromObject(groupList);
				JSONArray dataJsonArray = new JSONArray();
				JSONObject resultJson = new JSONObject();
				resultJson.element("status", "1");
				resultJson.element("error_code", "0");
				for (int i = 0; i < selectResult.size(); i++) {
					Object[] group1 =(Object[]) groupList.get(i);
					JSONObject dataJsonObject = new JSONObject();
					dataJsonObject.element("groupname", (String) group1[1]);
					dataJsonObject.element("groupTreeList", (String) group1[2]);
					dataJsonArray.add(i, dataJsonObject);
				}
				resultJson.element("data", dataJsonArray);
				out.print(resultJson);
			}else if(selectParam.equals("searchPersionList")){

			}
	    	LOG.info("返回json数据了============Test111");
	    	out.flush();
	    	out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//查询用户拥有的权限
	@Action(value = "/selectTreeAction")
	public void  SelectTreeList(){
		try {
			out = response.getWriter();
			String selectusername = request.getParameter("username");
			List selectTreeList=userServiceDao.searchListTree(selectusername);
			Map childMap = new HashedMap();
			JSONArray childArray = new JSONArray();
			int chileIndex = 0 ;
			for(int i=0;i<selectTreeList.size();i++){
				Map treeurlMap = (Map)selectTreeList.get(i);
				JSONObject dataJson = new JSONObject();
				if((Integer)treeurlMap.get("parentid") == 0){
					dataJson.element("text",treeurlMap.get("treename"));
					dataJson.element("nodes",new JSONArray());
					childMap.put(treeurlMap.get("treeid").toString(),chileIndex);
					childArray.add(chileIndex,dataJson);
					chileIndex++;
				}else{
					int parentidIndex =(Integer) childMap.get(treeurlMap.get("parentid").toString());
					dataJson.element("text",treeurlMap.get("treename"));
					dataJson.element("href",treeurlMap.get("url"));
					childArray.getJSONObject(parentidIndex).getJSONArray("nodes").add(dataJson);
				}
			}
			JSONObject resultJson = new JSONObject();
			resultJson.element("treeData","'"+childArray.toString()+"'");
			out.write(resultJson.toString());
			out.flush();
			out.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}