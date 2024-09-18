package com.jpabooks.mapper;

import com.jpabooks.dto.BookDTO;
import com.jpabooks.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {AuthorMapper.class})

public interface BookMapper {
    BookDTO mapToBookDTO(Book book);
    Book unMap(BookDTO bookDTO);
}
