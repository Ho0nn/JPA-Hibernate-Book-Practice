package com.jpabooks.base;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity<ID>, ID extends Number> extends  JpaRepository<T,ID>{
       @Modifying
       @Transactional
    @Query("update #{entityName} ent SET ent.statusCode= :statusCode WHERE ent.id= :id")
    void updateEntity(@Param("id") ID id, @Param("statusCode") Integer statusCode);
}
