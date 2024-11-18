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
    private String contactNumber;

    // Owner, Staff
    @Column(length =100, nullable = false)
    private String userType;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}