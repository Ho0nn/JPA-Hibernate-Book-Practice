package com.jpabooks.service;

import com.jpabooks.entity.AutherSearch;
import com.jpabooks.repository.AutherRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.entity.Auther;
import com.jpabooks.repository.AutherSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutherService extends BaseService<Auther,Long> {
    @Autowired
    private AutherRepo autherRepo;

    public AutherService(AutherRepo authRepo) {
       super(authRepo);
        this.autherRepo = authRepo;
    }
    @Override
    public Auther update(Auther entity) {
        Auther auther=findById(entity.getId());
        auther.setName(entity.getName());
        return super.update(entity);
    }
    public List<Auther> findByAutherSpec(AutherSearch search) {
        AutherSpec autherSpec = new AutherSpec(search);
            return autherRepo.findAll(autherSpec);

    }
}
