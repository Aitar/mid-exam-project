package com.example.dao;

import com.example.model.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component("bookDao")
@Repository("bookDao")
public class BookDao {

    public BookDao(){}

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

    public Book getById(int id){
        return new Book();
    }

    public List<Book> gerAll(){
        List<Book> books = new ArrayList<Book>();
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            // do some work
            books = session.createQuery("from Book").list();
        }
        catch(Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return books;
    }

    public void insert(){

    }

    public void deleteById(int id){}

    public void update(Book book){}
}

