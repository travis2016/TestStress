package szc.users.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.opensymphony.xwork2.ActionSupport;
import com.szc.users.beans.UserBean;
import com.szc.users.service.UserService;
import com.szc.users.service.Impl.UserServiceImpl;
import com.szc.util.MD5Util;

/**
 * 
 * @author TravisSong
 * Action负责处理请求
 * action调用service的方法，service调用dao中的方法
 */
@Controller("RegisterAction")
@Namespace("/regist")
@Scope("prototype")
public class RegisterAction extends ActionSupport {  
  
    private static final long serialVersionUID = 1L; 
    
    @Resource(name ="UserService")
    private UserServiceImpl userService;  
  
    //public UserBean user; 
    private HttpServletRequest request;
  	private HttpServletResponse response;
  	private ServletContext Context;
  	private WebApplicationContext ctx;
  	
  	
	public RegisterAction() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");//设置传输编码
		Context = ServletActionContext.getServletContext();
		ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(Context);
	}
  
    public UserServiceImpl getUserService() {
		return userService;
	}

    @Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@Action(value = "/regAction")//ajax的方法，返回的Type必须是json
	public void registUser() {  
        try {  
        	System.out.println("执行注册123");
        	PrintWriter out=null;
        	String jsonString = IOUtils.toString(request.getInputStream());
        	JSONObject paramJson = new JSONObject(jsonString);    
//        	System.out.println(paramJson.getString("nickname"));
        	UserBean registerUser=new UserBean(paramJson.getString("username"),MD5Util.string2MD5(paramJson.getString("password"))
        						,Integer.parseInt(paramJson.getString("gender")), java.net.URLDecoder.decode(paramJson.getString("nickname"),"UTF-8"),3);
        	userService.save(registerUser);	// register user 
            out = response.getWriter();
            String resultString = "{\"status\":1}";
            JSONObject resultJson = new JSONObject(resultString); 
            System.out.println(resultJson);
            out.println(resultJson);
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
    }       

	/**
	 * 判断用户是否存在。用户存在，则返回true，用户不存在，则返回false
	 */
	@Action(value="/judgeUserExitAction")
	public void judgeUser(){
		try {
			System.out.println("判断用户是否已注册");
			UserBean user = new UserBean(request.getParameter("userName"));
			PrintWriter out=null;
			System.out.println("username="+user.getUserName());
			boolean judgeExit=userService.isExitUser(user.getUserName());
			out = response.getWriter();
			if(judgeExit==true){
				out.print("true");
			}else{
				out.print("false");
			}
			out.flush();
			out.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
	
}  