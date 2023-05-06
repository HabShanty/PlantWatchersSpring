package com.plantwatchersspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long score;
    private String name;
    private String email;
    private String password;


    protected Users(){}


    public Users(long score, String name, String email, String password) {
        this.score = score;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString(){
        return String.format(
            "User[id=%d, name='%s', email='%s', password='%s', score=%d]",
            id, name, email, password, score);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

