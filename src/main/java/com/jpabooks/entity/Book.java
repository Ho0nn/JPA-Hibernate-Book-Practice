package com.jpabooks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jpabooks.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@NamedEntityGraph(name = "loadAuther", attributeNodes = @NamedAttributeNode("auther"))
@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long> {

    @NotNull(message = "Enter book name")
    private String name;

    @Min(value = 5)
    @Max(value = 500)
    private double price;

    private double disc;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Auther auther;

    public Book() {}

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
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
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", disc=" + disc +
                ", auther=" + auther.getName() +
                '}';
    }
}
