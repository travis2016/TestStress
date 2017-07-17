package com.szc.users.action;

import com.szc.users.beans.RoleGroupBean;
import com.szc.users.dao.Impl.TreeDaoImpl;
import com.szc.users.service.Impl.UserCommonManagerServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by travissong on 2017/7/17.
 * 处理登录后的组的Action(修改用户信息、管理员查询用户)
 */
@Scope("prototype")
@Namespace("/group")
@ParentPackage(value="test")
public class GroupAction {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext Context;
    private WebApplicationContext ctx;
    private PrintWriter out;
    private UserCommonManagerServiceImpl groupServiceDao;
    private TreeDaoImpl treeDaoControl;

    public UserCommonManagerServiceImpl getGroupServiceDao() {
        return groupServiceDao;
    }
    @Autowired
    public void setGroupServiceDao(UserCommonManagerServiceImpl groupServiceDao) {
        this.groupServiceDao = groupServiceDao;
    }

    public TreeDaoImpl getTreeDaoControl() { return treeDaoControl;}
    @Autowired
    public void setTreeDaoControl(TreeDaoImpl treeDaoControl) {
        this.treeDaoControl = treeDaoControl;
    }

    public GroupAction() {
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");//设置传输编码
        Context = ServletActionContext.getServletContext();
        ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(Context);
    }

    /**
     * 查询指定组用哪些权限
     **/
    @Action(value = "/selectGroupAction"
            ,results = { @Result(name = "add", location = "/login.jsp")}
            ,interceptorRefs= {@InterceptorRef(value="checkLoginStack")})
    public void selectGroup(){
        try{
            out= response.getWriter();
            String selectParam = request.getParameter("search");
            String searchGroup =URLDecoder.decode(request.getParameter("groupname"),"utf-8");
            if(selectParam.equals("searchSingerGroup")){
                List groupList = groupServiceDao.SearchGroup(searchGroup);
                //首先查到对应组有哪些权限id，再根据权限id查权限列表名称
                JSONArray selectResult = JSONArray.fromObject(groupList);
                JSONObject selectJson = selectResult.getJSONObject(0);
                //根据treeid查treelist
                System.out.println("treeids===="+selectJson.getString("treeidlist"));
                List groupNameList = treeDaoControl.selectTreeName(selectJson.getString("treeidlist"));
                JSONArray groupTreeNameArray = JSONArray.fromObject(groupNameList);
                System.out.println(groupTreeNameArray.toString());
                JSONObject resultJson = new JSONObject();
                resultJson.element("status", "1");
                resultJson.element("error_code", "0");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
