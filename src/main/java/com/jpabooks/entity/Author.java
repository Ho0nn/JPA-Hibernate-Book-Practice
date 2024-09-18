package com.jpabooks.entity;

import com.jpabooks.base.BaseEntity;
import com.jpabooks.validator.IpAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Author Entity")
@SQLDelete(sql = "update authors set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@Entity
@Table(name = "authors")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Author extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 5270010967823899971L;

    @NotBlank
    private String name;

    @IpAddress()
    private String ipAddress;

    @Email(message = "{validation.constraints.email.message}")
    private String email;

    private String imgPath;
//    @OneToMany(mappedBy = "author")
//    private List<Book> books;

}
