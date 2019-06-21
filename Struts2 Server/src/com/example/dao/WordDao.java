package com.example.dao;

import com.example.model.Word;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("wordDao")
@Repository("wordDao")
public class WordDao {
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

    public WordDao(){ }

    /**
     * 返回所有的单词信息的Arraylist
     * @return words 所有的单词的列表
     * @throws SQLException
     */
    public List getAll() throws SQLException {
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List words = new ArrayList<>();      //用于存储每个单词
        try {
            // do some work
            words = session.createQuery("from Word").list();
        }
        catch(Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return words;
    }

    /**
     * 通过id定位返回记录
     * @param id
     * @return
     */
    public Word getById(int id){
        Word word = null;
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            word = (Word)session.get(Word.class, id);
            tx.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return word;
    }

    /**
     * 通过en定位返回记录（未完成）
     * @param en 英文释义
     * @return
     */
    public Word getByWordname(String en){
        Word word = null;
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            //do something
            tx.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return word;
    }

    /**
     * 向words表中插入一条记录
     * @param zh 中文
     * @param en 英文
     */
    public void inst(String zh, String en){
        Integer wordId = null;
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx = session.beginTransaction();
            Word word = new Word(en, zh);
            session.save(word);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    /**
     * 通过id删除一条记录并返回此记录
     * @param id
     * @return word 返回的记录
     */
    public Word delById(int id){
        Word word = new Word();
        Integer wordId = null;
        Session session = ourSessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx = session.beginTransaction();
            session.delete(word = (Word)session.get(Word.class, id));
            tx.commit();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return word;
    }
}
