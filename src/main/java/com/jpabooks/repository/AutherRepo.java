package com.jpabooks.repository;
import com.jpabooks.base.BaseRepo;
import com.jpabooks.entity.Auther;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AutherRepo extends BaseRepo<Auther,Long>, JpaSpecificationExecutor<Auther>{

    @Transactional
    @Query(value="UPDATE Auther a set a.isDeleted= false where a.id=?1")
    @Modifying
    public void restorById(Long id);
}
