package com.example.moviesearcher.repository;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRecommendationRepositoryImpl implements MovieRecommendationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Movie> findMoviesByPreferences(UserInfoDTO userInfoDTO, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        List<Predicate> predicates = buildPredicates(userInfoDTO, cb, root);
        query.select(root).where(predicates.toArray(new Predicate[0]));

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Movie> countRoot = countQuery.from(Movie.class);
        countQuery.select(cb.count(countRoot)).where(buildPredicates(userInfoDTO, cb, countRoot).toArray(new Predicate[0]));

        List<Movie> resultList = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, total);
    }

    private List<Predicate> buildPredicates(UserInfoDTO userInfoDTO, CriteriaBuilder cb, Root<Movie> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (userInfoDTO.getPreferredGenreIds() != null && !userInfoDTO.getPreferredGenreIds().isEmpty()) {
            predicates.add(root.get("genreIds").in(userInfoDTO.getPreferredGenreIds()));
        }

        List<Long> crewIds = userInfoDTO.getFavoriteActorIds() != null && !userInfoDTO.getFavoriteActorIds().isEmpty()
                ? userInfoDTO.getFavoriteActorIds()
                : userInfoDTO.getFavoriteDirectorIds();
        if (crewIds != null && !crewIds.isEmpty()) {
            predicates.add(root.get("crewMemberIds").in(crewIds));
        }

        if (userInfoDTO.getPreferredAgeRating() != null) {
            predicates.add(cb.equal(root.get("ageRating"), userInfoDTO.getPreferredAgeRating()));
        }

        if (userInfoDTO.getContentTypePreference() != null) {
            predicates.add(cb.equal(root.get("contentType"), userInfoDTO.getContentTypePreference()));
        }

        if (userInfoDTO.getMinDuration() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("duration"), userInfoDTO.getMinDuration()));
        }

        if (userInfoDTO.getMaxDuration() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("duration"), userInfoDTO.getMaxDuration()));
        }

        return predicates;
    }
}