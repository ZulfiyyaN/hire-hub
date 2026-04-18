package com.example.hirehub.model.enumeration;

public enum StatusJobPost {
    ACTIVE, //after admin accepted
    DELETED, //when user (admin or company) delete the post
    EXPIRED; //when pass expired date
}
