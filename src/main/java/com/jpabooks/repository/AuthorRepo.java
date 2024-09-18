package com.jpabooks.repository;

import com.jpabooks.base.BaseRepo;
import com.jpabooks.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends BaseRepo<Author, Long>, JpaSpecificationExecutor<Author> {

    @Transactional
    @Query("UPDATE Author a SET a.isDeleted = false WHERE a.id = ?1")
    @Modifying
    void restoreById(Long id);

    Optional<Author> findByEmail(String email);

    @Override
    List<Author> findAll();
}
