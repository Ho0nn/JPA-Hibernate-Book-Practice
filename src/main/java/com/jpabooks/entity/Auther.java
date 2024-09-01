package com.jpabooks.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpabooks.base.BaseEntity;
import com.jpabooks.validator.IpAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
//@SQLDelete(sql="update authors a set is_deleted = true where id=?")
//@Where(clause = "is_deleted=false")
@Entity
@Table(name = "authors")
public class Auther extends BaseEntity<Long> {

    @NotBlank
    private String name;
    //@Pattern(regexp="^([0-9]{1,3})\\.([0-9]{1,3}\\.([0-9]{1,3})\\.([0-9]{1,3}))$")
    @IpAddress(message = "Enter valid IP Address")
    private String ipAddress;

    @Email
    private String email;
//    @Formula("(select count(*) from books b where b.author_id = id)")
//    private long bookCnt;

    @JsonManagedReference
    @OneToMany(mappedBy = "auther", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Auther() {}

    public Auther(String name) {
        this.name = name;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setAuther(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAuther(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

//    public long getBookCnt() {
//        return bookCnt;
//    }
//
//    public void setBookCnt(long bookCnt) {
//        this.bookCnt = bookCnt;
//    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
