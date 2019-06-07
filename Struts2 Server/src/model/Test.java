package model;

import dao.UserDao;
import dao.WordDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(final String[] args) throws Exception {

        UserDao userDao = new UserDao();
        userDao.updateLastLogin(1);
        userDao.inst(new User("pidoudou", "980518", "pidoudou"));
        System.out.println();
    }
}