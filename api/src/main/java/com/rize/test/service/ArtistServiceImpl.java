package com.rize.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rize.test.model.Artist;
import com.rize.test.model.Category;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

	@PersistenceContext
	private EntityManager entityManager;

	public Page<Artist> searchArtists(String name, Category category, String birthdayMonth, Pageable pageable) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Artist> countRoot = countQuery.from(Artist.class);

		CriteriaQuery<Artist> criteriaQuery = criteriaBuilder.createQuery(Artist.class);
		Root<Artist> dataRoot = criteriaQuery.from(Artist.class);

		Predicate countSearchPredicates = buildSearchPredicates(name, category, birthdayMonth, criteriaBuilder, countRoot);
		Predicate dataSearchPredicates = buildSearchPredicates(name, category, birthdayMonth, criteriaBuilder, dataRoot);

		// Total count
		countQuery.select(criteriaBuilder.count(countRoot)).where(countSearchPredicates);
		Long count = entityManager.createQuery(countQuery).getSingleResult();

		// Pagination
		int pageSize = pageable.getPageSize();
		int firstResult = pageSize * pageable.getPageNumber();

		TypedQuery<Artist> dataQuery = entityManager.createQuery(criteriaQuery.select(dataRoot).where(dataSearchPredicates));
		List<Artist> results = dataQuery.setFirstResult(firstResult).setMaxResults(pageSize).getResultList();

		Page<Artist> page = new PageImpl<Artist>(results, pageable, count);

		return page;
	}

	private Predicate buildSearchPredicates(String name, Category category, String birthdayMonth,
			CriteriaBuilder criteriaBuilder, Root<Artist> root) {

		List<Predicate> predicates = new ArrayList<>();

		// Name search criterion
		if (StringUtils.hasLength(name)) {
			Predicate fullNamePredicate = criteriaBuilder.like(root.get("fullName"), "%" + name.toLowerCase() + "%");
			predicates.add(fullNamePredicate);
		}

		// Category search criterion
		if (category != null) {
			Predicate categoryPredicate = criteriaBuilder.equal(root.get("category"), category);
			predicates.add(categoryPredicate);
		}

		// Birthday month search criterion
		if (StringUtils.hasLength(birthdayMonth)) {
			Predicate birthdayMonthPredicate = criteriaBuilder.equal(root.get("birthdayMonth"), birthdayMonth);
			predicates.add(birthdayMonthPredicate);
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		Predicate searchPredicates = criteriaBuilder.and(predicatesArray);

		return searchPredicates;
	}

}
