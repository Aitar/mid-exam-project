package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "books", schema = "servlet_test", catalog = "")
public class Book {
    private int id;
    private String name;
    private int wordnum;

    // 无参数的构造器
    public Book() { }


    public Book(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.wordnum = price;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "wordnum")
    public int getWordnum() {
        return wordnum;
    }

    public void setWordnum(int wordnum) {
        this.wordnum = wordnum;
    }
}