package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieFindByTitleRepositoryImpl implements MovieFindByTitleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Movie> findByTitleContainingIgnoreCase(String query, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
        Root<Movie> movie = cq.from(Movie.class);

        Predicate titlePredicate = cb.like(cb.lower(movie.get("title")), "%" + query.toLowerCase() + "%");
        cq.where(titlePredicate);

        TypedQuery<Movie> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<Movie> resultList = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Movie> countRoot = countQuery.from(Movie.class);
        countQuery.select(cb.count(countRoot));
        countQuery.where(cb.like(cb.lower(countRoot.get("title")), "%" + query.toLowerCase() + "%"));

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, count);
    }
}
