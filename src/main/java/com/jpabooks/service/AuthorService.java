package com.jpabooks.service;

import com.jpabooks.entity.AuthorSearch;
import com.jpabooks.entity.Author;
import com.jpabooks.errors.DuplicateRecordException;
import com.jpabooks.repository.AuthorRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.repository.AuthorSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorService extends BaseService<Author, Long> {

    private final AuthorRepo authorRepo;

    @Override
    @Cacheable(value = "findAll", key = "#root.methodName")
    public List<Author> findAll() {
        return super.findAll();
    }

    @Override
    @Cacheable(value = "findById", key = "#id")
    public Author findById(Long id) {
        return super.findById(id);
    }

    @Override
    @CacheEvict(value = {"findAll", "findById"}, allEntries = true)
    public Author insert(Author entity) {
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            try {
                Author existingAuthor = findByEmail(entity.getEmail()).get();
                if (existingAuthor != null) {
                    throw new DuplicateRecordException("This Email is already found!");
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Error checking email existence", e);
            }
        }
        return super.insert(entity);
    }

    @Override
    @CacheEvict(value = {"findAll", "findById"}, allEntries = true)
    public Author update(Author entity) {
        Author author = findById(entity.getId());
        author.setName(entity.getName());
        return super.update(entity);
    }

    public List<Author> findByAuthorSpec(AuthorSearch search) {
        AuthorSpec authorSpec = new AuthorSpec(search);
        return authorRepo.findAll(authorSpec);
    }

    @Async
    @Cacheable(value = "findByEmail", key = "#email")
    public CompletableFuture<Author> findByEmail(String email) {
        log.debug("Finding author by email: {}", email);
        return CompletableFuture.supplyAsync(() -> authorRepo.findByEmail(email).orElse(null));
    }
}
