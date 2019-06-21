package com.example.dao;

import com.example.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Component("userDao")
@Repository("userDao")
public class UserDao {

    public UserDao() {
    }

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static Session getHibernateSession() {

        final SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        // factory = new Configuration().configure().buildSessionFactory();
        final Session session = sf.openSession();
        return session;
    }


    /**
     * 返回所有基本的用户信息的Arraylist
     *
     * @return
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException {
        User user;      //用于存储用户属性
        List<User> users = new ArrayList<>();      //用于存储每个用户
        final Session session = getHibernateSession();
        try {
            Transaction tx = session.beginTransaction();
            // do some work
            users = session.createQuery("from User").list();    //获取所有的用户
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }


    /**
     * 通过id定位返回记录
     *
     * @param id
     * @return
     */
    public User getById(int id) {
        User user = null;
        Integer userId = null;
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;

    }


    /**
     * 通过username定位返回记录
     *
     * @param username
     * @return
     */
    public User getByUsername(String username) {
        User user = null;
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = getById(getIdByUsername(username));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

        return user;
    }


    /**
     * 往wusers表中插入一条记录
     *
     * @param user
     */
    public void inst(User user) {
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        if(!tx.isActive()) tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }


    /**
     * 通过id删除一条记录并返回此记录
     *
     * @param id
     * @return user 返回的记录
     */
    public User delById(int id) {
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        Integer userId = null;

        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            session.delete(user = (User) session.get(User.class, id));
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }


    /**
     * 通过id跟新记录属性
     *
     * @param id     索引
     * @param massge 需要修改的信息
     */
    public void updateById(int id, User massge) {
        User user = new User();
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            user.setNickname(massge.getNickname());
            user.setDailyWords((massge.getDailyWords()));
            user.setPassword(massge.getPassword());
            user.setGender(massge.getGender());
            user.setStudied(massge.getStudied());
            user.setLastlogin(new Timestamp(new Date().getTime()));
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

    }


    /**
     * 登录时更新用户的最近登录日期
     *
     * @param id 需要更新用户的id
     */
    public void updateLastLogin(int id) {
        User user;
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            user.setLastlogin(new Timestamp(new Date().getTime()));
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    /**
     * 通过username找到对应的id
     *
     * @param username 用户名
     * @return 返回找到的id
     */
    public int getIdByUsername(String username) {
        int id = -1;
        List<User> users;

        try {
            users = getAllUsers();
        } catch (SQLException e) {
            System.out.println("获取用户列表失败！");
            e.printStackTrace();
            return id;
        }

        for (User user : users) {
            if (user.getUsername().equals(username))
                id = user.getId();
        }
        return id;
    }


    /**
     * 登录检查，检查user列表中是否有登录的用户信息
     * @param user
     * @return 找到返回id，没找到返回-1
     * @throws SQLException
     */
    public int userCheck(User user) throws SQLException {
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        if(!tx.isActive()) tx = session.beginTransaction();
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
                return u.getId();
        }
        session.close();
        return -1;
    }

}
