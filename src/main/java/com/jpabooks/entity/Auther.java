package com.jpabooks.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpabooks.base.BaseEntity;
import com.jpabooks.validator.IpAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



//@SQLDelete(sql="update authors a set is_deleted = true where id=?")
//@Where(clause = "is_deleted=false")
@Schema(name = "Authoe Entity")
@Entity
@Table(name = "authers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Auther extends BaseEntity<Long> {
    @NotBlank
    private String name;
    //@Pattern(regexp="^([0-9]{1,3})\\.([0-9]{1,3}\\.([0-9]{1,3})\\.([0-9]{1,3}))$")
    @IpAddress()
    private String ipAddress;

    private String imgPath;

    @Email(message = "{validation.constraints.email.message}")
    private String email;
//    @Formula("(select count(*) from books b where b.author_id = id)")
//    private long bookCnt;

    @JsonManagedReference
    @OneToMany(mappedBy = "auther", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();


}
