package org.codnect.moviebox.controller;

import org.codnect.moviebox.dto.GenreDTO;
import org.codnect.moviebox.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreDTO> findAllGenres() {
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO findGenre(@PathVariable("id") Long genreId) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDTO createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO updateGenre(@PathVariable("id") Long genreId, @Valid @RequestBody GenreDTO genreDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGenre(@PathVariable("id") Long genreId) {

    }

}
