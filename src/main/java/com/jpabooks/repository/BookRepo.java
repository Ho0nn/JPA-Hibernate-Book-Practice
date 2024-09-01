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
    @EntityGraph(value = "loadAuther")
    Optional<Book> findById(Long id);

    @Query("SELECT b FROM Book b WHERE (:name IS NULL OR b.name LIKE %:name%) " +
            "AND (:price IS NULL OR b.price >= :price) " +
            "AND (:autherId IS NULL OR b.auther.id = :autherId)")
    List<Book> filter(@Param("name") String name, @Param("price") Double price, @Param("autherId") Long autherId);
//    @Query("select book from Book book where book.id = :id")
//    Optional<Book> findByIdCustom(Long id);

    //    @EntityGraph(attributePaths = {"auther"})
//    @Query("select book from Book book join book.auther auther where " +
//            "(:name is null or book.name like :name)" +
//            "and(:price is null or book.price>= :price)" +
//            "and(:autherId is null or auther.id= autherId)")
    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.auther.id = :id")
    int deleteByAutherId(@Param("id") Long id);

    @Override
    @EntityGraph(attributePaths = {"auther"})
    List<Book> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.isDeleted = false WHERE b.auther.id = :autherId")
    void restoreByAutherId(@Param("autherId") Long autherId);
}
