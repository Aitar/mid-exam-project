package com.example.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.example.dao.UserDao;
import com.example.model.User;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@Results(@Result(name="success", type="redirectAction", params = {"actionName" , "user"}))
public class UserController extends ActionSupport implements ModelDriven<Object> {
    //定义Spring beans
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    // 封装 id 请求参数的属性
    private int id;
    private User model = (User)context.getBean("user");
    //依赖注入业务逻辑组件
    private UserDao userDao = (UserDao) context.getBean("userDao");
    private List<User> list;

    /**
     * 通过id请求的get方法
     * @param id 获取的用户的id
     */
    public void setId(int id){
        this.id = id;
        if(id > 0){
            this.model = userDao.getById(id);
        }
    }

    public int getId(){
        return this.id;
    }

    /**
     * 处理不带id参数的GET请求，并进入首页
     * @return
     */
    public void index(){
        try {
            list = userDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理带 id 参数的 GET 请求，返回指定id的用户
     * @return
     */
    public void show() { }

    /**
     * 处理不带id参数的POST请求
     * @return
     */
    public HttpHeaders create(){

        DefaultHttpHeaders defaultHttpHeaders = new DefaultHttpHeaders("edit");

        if(model.getNickname() == null){
            //登录的分支
            try {
                int id = userDao.userCheck(model);
                if(id != -1){
                    //登录成功
                    defaultHttpHeaders.withStatus(200+id);
                    defaultHttpHeaders.withETag(id);
                    userDao.updateLastLogin(id);
                }else {
                    //登录失败
                    defaultHttpHeaders.withStatus(500);
                    defaultHttpHeaders.withETag("User not found");
                }
            }
            catch (SQLException e) {
                //SQL出错
                e.printStackTrace();
                defaultHttpHeaders.withETag("File to get all users");
                defaultHttpHeaders.withStatus(500);
            }
        }else if(model.getId() == 0){
            //注册的分支
            try {
                userDao.inst(model);
            }catch (Exception e){
                //注册失败
                e.printStackTrace();
                defaultHttpHeaders.withETag("File to register");
                defaultHttpHeaders.withStatus(500);
            }
        }else if(model.getId() != 0){
            //更新信息的分支
            userDao.updateById(model.getId(), model);
        }

        return defaultHttpHeaders;
    }

    // 进入编辑页面 (user-edit.jsp)
    //处理带id请求参数，且指定操作edit资源的GET请求
    public String edit()
    {
        return "edit";
    }

    //处理不带id请求参数，且制定操作edit资源的GET请求
    public String editNew() {
        model = new User();
        return "editNew";
    }

    //处理带参数id的post请求
    public String update() {
        userDao.updateById(id, model);
        return SUCCESS;
    }

    //处理带参数id的delete请求
    public String destroy() {
        userDao.delById(id);
        addActionMessage("Delete user whose id is " + id + " successfully！");
        return "success";
    }


    @Override
    public Object getModel() {
//        userDao.updateById(id, model);
        return (list != null ? list : model);
    }
}
