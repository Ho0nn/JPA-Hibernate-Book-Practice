package com.jpabooks.service;

import com.jpabooks.entity.AutherSearch;
import com.jpabooks.errors.DuplicateRecordException;
import com.jpabooks.repository.AutherRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.entity.Auther;
import com.jpabooks.repository.AutherSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutherService extends BaseService<Auther,Long> {
    @Autowired
    private AutherRepo autherRepo;
    Logger log = LoggerFactory.getLogger(AutherService.class);
    @Override
    public Auther insert(Auther entity) {
        if (!entity.getEmail().isEmpty() && entity.getEmail()!=null){
            Optional<Auther> auther = findByEmail(entity.getEmail());
            //System.out.println("email is >> "+entity.getEmail());
            log.info("auther name  {} and email is {}",entity.getName(),entity.getEmail());
            if(auther.isPresent())throw new DuplicateRecordException("This Email already found!");
        }
        return super.insert(entity);
    }

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
    private Optional<Auther> findByEmail(String email){
        return autherRepo.findByEmail(email);
    }

}
