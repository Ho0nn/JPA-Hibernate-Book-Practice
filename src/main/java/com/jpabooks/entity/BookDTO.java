package com.jpabooks.entity;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BookDTO {
    private Long id;
    @NotBlank
    private String name;
    @Min(100)
    @Max(500)
    private double price;
    @NotNull
   private Auther auther;


}
