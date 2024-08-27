package com.jpabooks.service;

import com.jpabooks.Repository.AutherRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.entity.Auther;
import org.springframework.stereotype.Service;

@Service
public class AutherService extends BaseService<Auther,Long> {
    private final AutherRepo authRepo;

    public AutherService(AutherRepo authRepo) {
       super();
        this.authRepo = authRepo;
    }
    @Override
    public Auther update(Auther entity) {
        Auther auther=findById(entity.getId());
        auther.setName(entity.getName());
        return super.update(entity);
    }
}
