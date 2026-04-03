package com.example.hirehub.model.enumeration;

public enum StatusJobPost {
    PENDING, //after sharing the post is in PENDING status untill admin accept
    ACTIVE, //after admin accepted
    DELETED, //when user (admin or company) delete the post
    EXPIRED; //when pass expired date
}
