package com.jpabooks.repository;
import com.jpabooks.base.BaseRepo;
import com.jpabooks.entity.Auther;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AutherRepo extends BaseRepo<Auther,Long>, JpaSpecificationExecutor<Auther>{

    @Transactional
    @Query(value="UPDATE Auther a set a.isDeleted= false where a.id=?1")
    @Modifying
    public void restorById(Long id);

    @EntityGraph(attributePaths = "books")
    Optional<Auther> findByEmail(String email);

    @Override
    @EntityGraph(attributePaths = "books")
    List<Auther>findAll();
    @Override
    @EntityGraph(attributePaths = "books")
    Optional<Auther>findById(Long id);
}
