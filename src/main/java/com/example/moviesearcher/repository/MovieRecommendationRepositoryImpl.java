package com.example.moviesearcher.repository;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
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

        Expression<Integer> score = calculateScore(userInfoDTO, cb, root, query);
        query.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.desc(score));

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
        List<Predicate> minimalPredicates = new ArrayList<>();

        // Условие для жанров
        if (userInfoDTO.getPreferredGenreIds() != null && !userInfoDTO.getPreferredGenreIds().isEmpty()) {
            Join<Movie, Long> genreJoin = root.join("genreIds", JoinType.LEFT);
            minimalPredicates.add(genreJoin.in(userInfoDTO.getPreferredGenreIds()));
        }

        // Условие для актёров и режиссёров
        List<Long> crewIds = new ArrayList<>();
        if (userInfoDTO.getFavoriteActorIds() != null && !userInfoDTO.getFavoriteActorIds().isEmpty()) {
            crewIds.addAll(userInfoDTO.getFavoriteActorIds());
        }
        if (userInfoDTO.getFavoriteDirectorIds() != null && !userInfoDTO.getFavoriteDirectorIds().isEmpty()) {
            crewIds.addAll(userInfoDTO.getFavoriteDirectorIds());
        }
        if (!crewIds.isEmpty()) {
            Join<Movie, Long> crewJoin = root.join("crewMemberIds", JoinType.LEFT);
            minimalPredicates.add(crewJoin.in(crewIds));
        }

        // Условие для возрастного рейтинга
        if (userInfoDTO.getPreferredAgeRating() != null) {
            minimalPredicates.add(cb.equal(root.get("ageRating"), userInfoDTO.getPreferredAgeRating()));
        }

        // Условие для типа контента
        if (userInfoDTO.getContentTypePreference() != null) {
            minimalPredicates.add(cb.equal(root.get("contentType"), userInfoDTO.getContentTypePreference()));
        }

        // Условие для минимальной длительности
        if (userInfoDTO.getMinDuration() != null) {
            minimalPredicates.add(cb.greaterThanOrEqualTo(root.get("duration"), userInfoDTO.getMinDuration()));
        }

        // Условие для максимальной длительности
        if (userInfoDTO.getMaxDuration() != null) {
            minimalPredicates.add(cb.lessThanOrEqualTo(root.get("duration"), userInfoDTO.getMaxDuration()));
        }

        // Формирование финального предиката
        if (!minimalPredicates.isEmpty()) {
            predicates.add(cb.or(minimalPredicates.toArray(new Predicate[0]))); // OR для мягкой фильтрации
            // Или используйте cb.and(...) для строгой фильтрации
        } else {
            predicates.add(cb.equal(cb.literal(1), 0)); // Ложное условие
        }

        return predicates;
    }

    private Expression<Integer> calculateScore(UserInfoDTO userInfoDTO, CriteriaBuilder cb, Root<Movie> root, CriteriaQuery<?> query) {
        List<Expression<Integer>> scoreParts = new ArrayList<>();

        if (userInfoDTO.getPreferredGenreIds() != null && !userInfoDTO.getPreferredGenreIds().isEmpty()) {
            Subquery<Long> genreSubquery = query.subquery(Long.class);
            Root<Movie> genreSubRoot = genreSubquery.correlate(root);
            Join<Movie, Long> subGenreJoin = genreSubRoot.join("genreIds");
            genreSubquery.select(cb.count(subGenreJoin))
                    .where(subGenreJoin.in(userInfoDTO.getPreferredGenreIds()));
            scoreParts.add(cb.toInteger(genreSubquery));
        }

        List<Long> crewIds = new ArrayList<>();
        if (userInfoDTO.getFavoriteActorIds() != null && !userInfoDTO.getFavoriteActorIds().isEmpty()) {
            crewIds.addAll(userInfoDTO.getFavoriteActorIds());
        }
        if (userInfoDTO.getFavoriteDirectorIds() != null && !userInfoDTO.getFavoriteDirectorIds().isEmpty()) {
            crewIds.addAll(userInfoDTO.getFavoriteDirectorIds());
        }
        if (!crewIds.isEmpty()) {
            Subquery<Long> crewSubquery = query.subquery(Long.class);
            Root<Movie> crewSubRoot = crewSubquery.correlate(root);
            Join<Movie, Long> subCrewJoin = crewSubRoot.join("crewMemberIds");
            crewSubquery.select(cb.count(subCrewJoin))
                    .where(subCrewJoin.in(crewIds));
            scoreParts.add(cb.toInteger(crewSubquery));
        }

        if (userInfoDTO.getPreferredAgeRating() != null) {
            Expression<Integer> ageRatingScore = cb.<Integer>selectCase()
                    .when(cb.equal(root.get("ageRating"), userInfoDTO.getPreferredAgeRating()), 1)
                    .otherwise(0);
            scoreParts.add(ageRatingScore);
        }

        if (userInfoDTO.getContentTypePreference() != null) {
            Expression<Integer> contentTypeScore = cb.<Integer>selectCase()
                    .when(cb.equal(root.get("contentType"), userInfoDTO.getContentTypePreference()), 1)
                    .otherwise(0);
            scoreParts.add(contentTypeScore);
        }

        if (userInfoDTO.getMinDuration() != null) {
            Expression<Integer> minDurationScore = cb.<Integer>selectCase()
                    .when(cb.greaterThanOrEqualTo(root.get("duration"), userInfoDTO.getMinDuration()), 1)
                    .otherwise(0);
            scoreParts.add(minDurationScore);
        }

        if (userInfoDTO.getMaxDuration() != null) {
            Expression<Integer> maxDurationScore = cb.<Integer>selectCase()
                    .when(cb.lessThanOrEqualTo(root.get("duration"), userInfoDTO.getMaxDuration()), 1)
                    .otherwise(0);
            scoreParts.add(maxDurationScore);
        }

        if (scoreParts.isEmpty()) {
            return cb.literal(0);
        } else {
            Expression<Integer> totalScore = scoreParts.get(0);
            for (int i = 1; i < scoreParts.size(); i++) {
                totalScore = cb.sum(totalScore, scoreParts.get(i));
            }
            return totalScore;
        }
    }
}