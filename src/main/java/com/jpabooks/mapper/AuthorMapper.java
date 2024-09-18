package com.jpabooks.mapper;

import com.jpabooks.dto.AuthorDTO;
import com.jpabooks.entity.Author;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
//    @Mapping(target = "authorName", source = "author.name")
//    @Mapping(target = "authorEmail", source = "author.email")
    AuthorDTO maptoDTO(Author author);
//    @Mapping(source = "authorName", target = "author.name")
//    @Mapping(source = "authorEmail", target = "author.email")
    Author maptoEntity(AuthorDTO dto);

    @AfterMapping
    default void afterMapping(@MappingTarget Author entity, AuthorDTO  dto) {}
}
