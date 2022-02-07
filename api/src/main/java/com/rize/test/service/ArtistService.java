package com.rize.test.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rize.test.model.Artist;
import com.rize.test.model.Category;

public interface ArtistService {

	Page<Artist> searchArtists(String fullName, Category category, String birthdayMonth, Pageable pageable);

}
