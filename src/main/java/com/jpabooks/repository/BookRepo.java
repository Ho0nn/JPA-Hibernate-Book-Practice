package com.jpabooks.repository;

import com.jpabooks.base.BaseRepo;
import com.jpabooks.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
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

//    @EntityGraph(attributePaths = {"auther"})
//    @Query("select book from Book book join book.auther auther where " +
//            "(:name is null or book.name like :name)" +
//            "and(:price is null or book.price>= :price)" +
//            "and(:autherId is null or auther.id= autherId)")
//    List<Book>filter(String name,double price, Long autherId);



    @Transactional
    @Modifying
    @Query("delete from Book book where book.auther.id =:id")
    int deleteByAuther(Long id);


    @Override
    @EntityGraph(attributePaths = {"auther"} )
    List<Book> findAll();

    @Transactional
    @Modifying
    @Query("delete from Book where auther.id=id")
    int deleteByAutherId(Long id);

//    @Transactional
//    @Query(value = "update from Book book SET book.isDeleted = false where book.auther.id=?1 ")
//    @Modifying
//    public void restoreByAutherId(Long autherId);

}
