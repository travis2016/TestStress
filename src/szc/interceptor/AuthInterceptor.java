package szc.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * 
 * @author travissong
 * 拦截器1
 *
 */
public class AuthInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "";
		Map<String, Object> session = invocation.getInvocationContext().getContext().getSession();
		if(session.get("user")!=null){
			System.out.println("有权限"+session.get("user"));
			return invocation.invoke();
		}
		System.out.println("执行这里");
		ActionContext.getContext().put("message", "您无权执行该操作！");
		return "add";
		
//        ActionContext ctx = invocation.getInvocationContext();
//        //获取拦截的Action名称
//        String invocationAction=invocation.getAction().getClass().getName();
//        String invocationname= invocationAction.substring(invocationAction.lastIndexOf(".")+1,invocationAction.length());
//        System.out.println("invocationAction=="+invocationAction);
//        Map session = ctx.getSession();
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

}
