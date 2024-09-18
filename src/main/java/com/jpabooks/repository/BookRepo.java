package com.jpabooks.repository;

import com.jpabooks.base.BaseRepo;
import com.jpabooks.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends BaseRepo<Book, Long> {

    @Override
    @EntityGraph(value = "loadAuthor")
    Optional<Book> findById(Long id);

    @Query("SELECT b FROM Book b WHERE (:name IS NULL OR b.name LIKE %:name%) " +
            "AND (:price IS NULL OR b.price >= :price) " +
            "AND (:autherId IS NULL OR b.author.id = :authorId)")
    List<Book> filter(@Param("name") String name, @Param("price") Double price, @Param("authorId") Long authorId);
//    @Query("select book from Book book where book.id = :id")
//    Optional<Book> findByIdCustom(Long id);

    //    @EntityGraph(attributePaths = {"author"})
//    @Query("select book from Book book join book.author author where " +
//            "(:name is null or book.name like :name)" +
//            "and(:price is null or book.price>= :price)" +
//            "and(:authorId is null or author.id= authorId)")
    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.author.id = :id")
    int deleteByAuthorId(@Param("id") Long id);

    @Override
    @EntityGraph(attributePaths = {"author"})
    List<Book> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.isDeleted = false WHERE b.author.id = :authorId")
    void restoreByAuthorId(@Param("authorId") Long authorId);
}
