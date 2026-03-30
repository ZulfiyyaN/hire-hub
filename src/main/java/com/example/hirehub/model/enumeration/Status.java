package com.example.hirehub.model.enumeration;

public enum Status {
    PENDING, //after register the profil is in PENDING status untill admin accept
    ACTIVE, //after admin accept
    DELETED, //when user (candidate or company) delete the profil or job post
    BLOCKED; //when admin block the profil or job post
}
