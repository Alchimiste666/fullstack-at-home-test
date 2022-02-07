package com.rize.test.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rize.test.model.Artist;
import com.rize.test.model.Category;
import com.rize.test.respository.ArtistRepository;
import com.rize.test.service.ArtistService;

@RestController
@RequestMapping(path = "/artists", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistController {

	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private ArtistService artistService;

	@PostMapping
	public ResponseEntity<Artist> createArtist(@RequestBody @Valid Artist artist) throws URISyntaxException {
		Artist createdArtist = artistRepository.save(artist);
		URI uri = new URI("/artists/" + createdArtist.getId());
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<Page<Artist>> searchArtists(Pageable pageable, String name, Category category, String birthdayMonth) {
		Page<Artist> artists = artistService.searchArtists(name, category, birthdayMonth, pageable);
		return ResponseEntity.ok(artists);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Artist> searchArtistById(@PathVariable Integer id) {
		Optional<Artist> artist = artistRepository.findById(id);
		return artist.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(artist.get());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteArtist(@PathVariable Integer id) {
		if (artistRepository.existsById(id)) {
			artistRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
