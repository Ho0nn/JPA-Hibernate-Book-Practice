package com.jpabooks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

@NamedEntityGraph(name = "loadAuther", attributeNodes = @NamedAttributeNode("auther"))
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    //@Transient
    private double disc;

   // @Formula("(select count(*) from books b where b.author_id = author_id)")
    private long bookCnt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Auther auther;
    public Book() {}

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDisc() {
        return disc;
    }

    public void setDisc(double disc) {
        this.disc = disc;
    }

    public long getBookCnt() {
        return bookCnt;
    }

    public void setBookCnt(long bookCnt) {
        this.bookCnt = bookCnt;
    }

    public Auther getAuther() {
        return auther;
    }

    public void setAuther(Auther auther) {
        this.auther = auther;
    }

    @PostLoad
    private void calcDisc() {
        this.setDisc(price * 0.25);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", disc=" + disc +
                ", bookCnt=" + bookCnt +
                '}';
    }
}
