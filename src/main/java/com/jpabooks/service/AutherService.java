package com.jpabooks.service;

import com.jpabooks.entity.AutherSearch;
import com.jpabooks.errors.DuplicateRecordException;
import com.jpabooks.repository.AutherRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.entity.Auther;
import com.jpabooks.repository.AutherSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Log4j2
public class AutherService extends BaseService<Auther, Long> {

    private final AutherRepo autherRepo;

    @Override
    @Cacheable(value = "findAll", key = "#root.methodName")
    public List<Auther> findAll() {
        return super.findAll();
    }

    @Override
    @Cacheable(value = "findById", key = "#id")
    public Auther findById(Long id) {
        return super.findById(id);
    }

    @Override
    @CacheEvict(value = {"findAll", "findById"}, allEntries = true)
    public Auther insert(Auther entity) {
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            CompletableFuture<Auther> autherFuture = findByEmail(entity.getEmail());
            try {
                Auther existingAuther = autherFuture.get();
                if (existingAuther != null) {
                    throw new DuplicateRecordException("This Email is already found!");
                }
            } catch (Exception e) {
                throw new RuntimeException("Error checking email existence", e);
            }
        }
        return super.insert(entity);
    }

    @Override
    @CacheEvict(value = {"findAll", "findById"}, allEntries = true)
    public Auther update(Auther entity) {
        Auther auther = findById(entity.getId());
        auther.setName(entity.getName());
        return super.update(entity);
    }

    public List<Auther> findByAutherSpec(AutherSearch search) {
        AutherSpec autherSpec = new AutherSpec(search);
        return autherRepo.findAll(autherSpec);
    }

    @Async
    @Cacheable(value = "findByEmail", key = "#email")
    public CompletableFuture<Auther> findByEmail(String email) {
        return CompletableFuture.supplyAsync(() -> autherRepo.findByEmail(email).orElse(null));
    }
}
