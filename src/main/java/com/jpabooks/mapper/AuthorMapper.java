package com.jpabooks.mapper;

import com.jpabooks.dto.AuthorDTO;
import com.jpabooks.entity.Author;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO maptoDTO(Author author);
    Author maptoEntity(AuthorDTO dto);

    @AfterMapping
    default void afterMapping(@MappingTarget Author entity, AuthorDTO  dto) {}
}
