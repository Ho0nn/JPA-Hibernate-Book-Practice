package com.jpabooks.Repository;

import com.jpabooks.base.BaseRepo;
import com.jpabooks.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends BaseRepo<Book,Long> {
    @Override
    //@EntityGraph(attributePaths = {"auther"} )
   //Using NamedGraph --> values
    @EntityGraph(value = "loadAuther" )
    Optional<Book> findById(Long id);


//    @Query("select book from Book book where book.id = :id")
//    Optional<Book> findByIdCustom(Long id);

    @Transactional
    @Modifying
    @Query("delete from Book book where book.auther.id =:id")
    int deleteByAuther(Long id);


    @Override
    @EntityGraph(attributePaths = {"auther"} )
    List<Book> findAll();

}
