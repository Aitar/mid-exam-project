package com.example.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "words", schema = "servlet_test", catalog = "")
public class Word {
    private int id;
    private String en;
    private String zh;

    public Word(){ }

    public Word(String en, String zh) {
        this.en = en;
        this.zh = zh;
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
    @Column(name = "en")
    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Basic
    @Column(name = "zh")
    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word that = (Word) o;
        return id == that.id &&
                Objects.equals(en, that.en) &&
                Objects.equals(zh, that.zh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, en, zh);
    }
}
