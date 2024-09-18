package com.jpabooks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jpabooks.base.BaseEntity;
import com.jpabooks.dto.AuthorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NamedEntityGraph(name = "loadAuthor", attributeNodes = @NamedAttributeNode("author"))
@Entity
@Table(name = "books")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book extends BaseEntity<Long> implements Serializable {
   // private static final long serialVersionUID = 5270010967823899971L;
    @NotNull(message = "Enter book name")
    private String name;

    @Min(value = 5)
    @Max(value = 500)
    private double price;

    private double disc;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

}
