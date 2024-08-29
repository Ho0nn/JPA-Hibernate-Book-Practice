package com.jpabooks.repository;

import com.jpabooks.entity.Auther;
import com.jpabooks.entity.AutherSearch;
import com.jpabooks.entity.Book;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AutherSpec implements Specification<Auther> {
    private AutherSearch search;

    public AutherSpec(AutherSearch search) {
        super();
        this.search = search;
    }
    @Override
    public Predicate toPredicate(Root<Auther> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

       Join<Auther,Book>bookJoin= root.join("books", JoinType.LEFT);

        //name
        if (search.getAuthorName()!=null && !search.getAuthorName().isEmpty())
            predicates.add(cb.like(root.get("name"),search.getAuthorName()));

        //email
        if (search.getAuthorEmail()!=null && !search.getAuthorEmail().isEmpty())
            predicates.add(cb.like(root.get("email"),search.getAuthorEmail()));
        //ipAddress
        if (search.getIpAddress()!=null && !search.getIpAddress().isEmpty())
            predicates.add(cb.like(root.get("ipAddress"),search.getIpAddress()));
        //bookName
        if (search.getBookName()!=null && !search.getBookName().isEmpty())
            predicates.add(cb.like(bookJoin.get("name"),search.getBookName()));
        //price
        if (search.getPrice()!=null )
            predicates.add(cb.like(bookJoin.get("price"),search.getBookName()));


        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
