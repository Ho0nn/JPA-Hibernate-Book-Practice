package com.jpabooks.service;

import com.jpabooks.Repository.AutherRepo;
import com.jpabooks.entity.Auther;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AutherService {
    private final AutherRepo authRepo;

    public AutherService(AutherRepo authRepo) {
        this.authRepo = authRepo;
    }

    public Auther findById(Long id) {
        return authRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));
    }
    public Auther getById(Long id) {
        return authRepo.getById(id);
    }

    public List<Auther> findAll() {
        return authRepo.findAll();
    }

    public Auther insert(Auther entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("ID should be null when inserting a new author.");
        }
        return authRepo.save(entity);
    }

    public List<Auther> insertAll(List<Auther> entities) {
        return authRepo.saveAll(entities);
    }

    public Auther update(Auther entity) {
        Auther auther = findById(entity.getId());
        auther.setName(entity.getName());
        return authRepo.save(auther);
    }

    public void deleteById(Long id) {
        authRepo.deleteById(id);
    }
}
