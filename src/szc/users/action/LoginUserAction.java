package szc.users.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.szc.users.beans.UserBean;
import com.szc.users.service.Impl.UserServiceImpl;
import com.szc.util.MD5Util;

/**
 * 
 * @author TravisSong
 * 处理登录后的用户的Action(修改用户信息、管理员查询用户)
 */
@Controller("LoginUserAction")
@Namespace("/logins")
@Scope("prototype")
public class LoginUserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Resource(name ="UserService")
    private UserServiceImpl userService;   
	  
	private HttpServletRequest request;
  	private HttpServletResponse response;
  	private ServletContext Context;
  	private WebApplicationContext ctx;
  	
    @Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
    
    public UserServiceImpl getUserService() {
		return userService;
	}

	public LoginUserAction() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		Context = ServletActionContext.getServletContext();
		ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(Context);
	}
	
	@Action(value = "/loginAction")
	public void login(){
		PrintWriter out=null;
		JSONObject resultjson = new JSONObject();
		try{	
			String jsonString = IOUtils.toString(request.getInputStream());
			System.out.println("用户参数：==============="+jsonString);
        	JSONObject paramJson = new JSONObject(jsonString); 
			UserBean loginrUser=new UserBean(paramJson.getString("username"),MD5Util.string2MD5(paramJson.getString("password")));
			out = response.getWriter();			
			boolean results=userService.userLogin(loginrUser.getUserName(), loginrUser.getPassword());
			System.out.println("返回结果："+results);
			if(results==true){
				String nicknames=userService.searchUserNickname(loginrUser.getUserName());
				System.out.println(nicknames);
				//从已经建好的Session中取数据，如果没有Session则自动创建  
                HttpSession session=request.getSession(true); 
                session.setAttribute("loginusername",nicknames);
//				ActionContext.getContext().getSession().put("user",loginrUser);
//				ActionContext.getContext().getSession().put("loginusername",nicknames);
				resultjson.append("status", 1);
				resultjson.append("nickname", nicknames);
				out.write(resultjson.toString());
			}else{
				resultjson.append("status",0);
				resultjson.append("error_code",20001);
				resultjson.append("errorMsg","用户名或密码错误");
				out.write(resultjson.toString());
			}
			 
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
}
