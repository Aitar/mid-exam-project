package com.example.model;

import com.example.dao.BookDao;
import com.example.dao.UserDao;
import com.example.dao.WordDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(final String[] args) throws Exception {
        BookDao wordDao = new BookDao();
        List<Book> wordArrayList = wordDao.gerAll();
        System.out.println();
    }
}