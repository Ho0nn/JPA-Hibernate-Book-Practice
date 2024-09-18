package com.jpabooks.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorSearch {
    private String authorName;
    private String authorEmail;
    private String ipAddress;
    private String bookName;
    private Double price;
}