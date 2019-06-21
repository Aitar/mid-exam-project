package com.example.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "wusers", schema = "servlet_test", catalog = "")
public class User {
    private int id;
    private int studied;
    private String username;
    private String password;
    private String nickname;
    private int dailyWords;
    private Timestamp lastlogin;
    private String gender;
    private int curbook;

    public User(){

    }

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.dailyWords = 25;
        this.lastlogin = new Timestamp(0);
    }

    public User(int id, String username, String password, String nickname, int dailyWords, Timestamp lastlogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.dailyWords = dailyWords;
        this.lastlogin = lastlogin;
    }

    public User(int id, String username, String password, String nickname, int dailyWords, Timestamp lastlogin, String gender, int studied, int curbook) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.dailyWords = dailyWords;
        this.lastlogin = lastlogin;
        this.gender = gender;
        this.studied = studied;
        this.curbook = curbook;
    }


    @Basic
    @Column(name = "curbook")
    public int getCurbook() {
        return curbook;
    }

    public void setCurbook(int curbook) {
        this.curbook = curbook;
    }

    @Basic
    @Column(name = "studied")
    public int getStudied() {
        return studied;
    }

    public void setStudied(int studied) {
        this.studied = studied;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "lastlogin")
    public Timestamp getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Timestamp lastlogin) {
        this.lastlogin = lastlogin;
    }

    @Basic
    @Column(name = "dailywords")
    public int getDailyWords(){
        return dailyWords;
    }

    public void setDailyWords(int dailyWords){
        this.dailyWords = dailyWords;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, nickname, gender);
    }
}
