package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Member")
public class Member {

    // 수정하기
    @Id
    @Column(nullable = false, length = 100)
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String contact_number;

    // Owner, Staff
    @Column(length =100, nullable = false)
    private String user_type;
    // 기본 생성자 필요
    public Member() {
    }

    // id 필드를 인수로 받는 생성자 추가
    public Member(String id) {
        this.id = id;
    }
    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void member(String id, String name, String password, String email, String contact_number, String user_type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.contact_number = contact_number;
        this.user_type = user_type;
    }
}