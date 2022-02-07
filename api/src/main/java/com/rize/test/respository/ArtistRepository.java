package com.rize.test.respository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rize.test.model.Artist;

@Repository
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {
	
}
