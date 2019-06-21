package com.example.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.example.dao.WordDao;
import com.example.model.Word;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;

@Controller
@Results(@Result(name="success", type="redirectAction", params = {"actionName" , "word"}))
public class WordController extends ActionSupport implements ModelDriven<Object> {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private int id;
    private Word model = (Word)context.getBean("word");
    private List<Word> list;
    WordDao wordDao = (WordDao)context.getBean("wordDao");

    public void setId(int id) {
        this.id = id;
        // 取得方法时顺带初始化 model 对象
        if(id > 0){
            this.model = wordDao.getById(id);
        }
    }

    public int getId() {
        return this.id;
    }

    //不带id的get请求    ./word
    public HttpHeaders index(){
        DefaultHttpHeaders defaultHttpHeaders = new DefaultHttpHeaders();
        try {
            list = wordDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            defaultHttpHeaders.withStatus(500);
            defaultHttpHeaders.withETag("File to get all words");
        }
        defaultHttpHeaders.withStatus(200);
        defaultHttpHeaders.withETag("Success");
        return defaultHttpHeaders;
    }

    //带id的get请求    ./word/{id}
    public HttpHeaders show(){
        DefaultHttpHeaders defaultHttpHeaders = new DefaultHttpHeaders();
        defaultHttpHeaders.withStatus(200);
        defaultHttpHeaders.withETag(model);
        return defaultHttpHeaders;
    }

    // 实现 ModelDriven 接口必须实现的 getModel 方法
    public Object getModel() { return list != null ? list : model; }

}
