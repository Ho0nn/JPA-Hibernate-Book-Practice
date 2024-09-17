package com.jpabooks.base;

import com.jpabooks.errors.RecordNotFoundException;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Optional;

@MappedSuperclass
//@AllArgsConstructor
//@NoArgsConstructor(force = true)
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Number> {

    @Autowired
    private BaseRepo<T, ID> baseRepo;

    @Autowired
    private MessageSource messageSource;

    public T findById(ID id) {
        Optional<T> entity = baseRepo.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            String[] msgparam = {id.toString()};
            String msg = messageSource.getMessage("validation.recordNotFound.message", msgparam, LocaleContextHolder.getLocale());
            throw new RecordNotFoundException(msg);
        }
    }

    public T getById(ID id) {
        return baseRepo.getById(id);
    }

    public List<T> findAll() {
        return baseRepo.findAll();
    }

    public T insert(T entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("ID should be null when inserting a new entity.");
        }
        return baseRepo.save(entity);
    }

    public List<T> insertAll(List<T> entities) {
        return baseRepo.saveAll(entities);
    }

    public T update(T entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("ID should not be null when updating an existing entity.");
        }
        return baseRepo.save(entity);
    }

    public void deleteById(ID id) {
        baseRepo.deleteById(id);
    }
}
