 package com.jpabooks.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpabooks.base.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Auther extends BaseEntity<Long> {


    private String name;

    // @Formula("(select count(*) from books b where b.auther_id = id)")
    private long bookCnt;

    @JsonManagedReference
    @OneToMany(mappedBy = "auther")
    private List<Book> books = new ArrayList<>();



    public Auther() {}

    public Auther(String name) {
        this.name = name;
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

    public long getBookCnt() {
        return bookCnt;
    }

    public void setBookCnt(long bookCnt) {
        this.bookCnt = bookCnt;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
