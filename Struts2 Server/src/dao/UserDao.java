package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class UserDao {
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

    Session session = ourSessionFactory.openSession();
    Transaction tx = session.beginTransaction();

    public UserDao() {
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
        final Session session = getSession();
        try {
            // do some work
            users = session.createQuery("from User").list();    //获取所有的用户
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

        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;

    }


    /**
     * 通过username定位返回记录（未完成）
     *
     * @param username
     * @return
     */
    public User getByUsername(String username) {
        User user = null;

        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = (User) session.get(User.class, username);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return user;
    }


    /**
     * 往wusers表中插入一条记录
     *
     * @param user
     */
    public void inst(User user) {
        if(!tx.isActive()) tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }


    /**
     * 通过id删除一条记录并返回此记录
     *
     * @param id
     * @return user 返回的记录
     */
    public User delById(int id) {
        User user = new User();
        Integer userId = null;

        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            session.delete(user = (User) session.get(User.class, id));
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
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
        User user;

        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            user.setNickname(massge.getNickname());
            user.setDailyWords((massge.getDailyWords()));
            user.setPassword(massge.getPassword());
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }


    /**
     * 登录时更新用户的最近登录日期
     *
     * @param id 需要更新用户的id
     */
    public void updateLastLogin(int id) {
        User user;

        try {
            if(!tx.isActive()) tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            user.setLastlogin(new Timestamp(new Date().getTime()));
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
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
     * @return
     * @throws SQLException
     */
    public boolean userCheck(User user) throws SQLException {
        if(!tx.isActive()) tx = session.beginTransaction();
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
                return true;
        }
        return false;
    }

    public void finalize() {
        session.close();
    }
}
