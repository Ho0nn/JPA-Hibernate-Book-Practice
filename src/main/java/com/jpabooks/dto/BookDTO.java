package com.jpabooks.dto;


import com.jpabooks.base.BaseDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO extends BaseDTO<Long> {

    @NotBlank
    private String name;
    @Min(100)
    @Max(500)
    private double price;
    @NotNull
   private AuthorDTO author;


}
