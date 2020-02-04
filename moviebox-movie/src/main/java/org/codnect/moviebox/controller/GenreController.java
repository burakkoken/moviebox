package org.codnect.moviebox.controller;

import org.codnect.moviebox.dto.GenreDTO;
import org.codnect.moviebox.mapper.GenreMapper;
import org.codnect.moviebox.model.Genre;
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
    private GenreMapper genreMapper;

    @Autowired
    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreDTO> findAllGenres() {
        return genreMapper.toGenreDTOList(genreService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO findGenre(@PathVariable("id") Long genreId) {
        return genreMapper.toGenreDTO(genreService.findGenre(genreId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDTO createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        Genre newGenre = genreMapper.toGenre(genreDTO);
        return genreMapper.toGenreDTO(genreService.createGenre(newGenre));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO updateGenre(@PathVariable("id") Long genreId, @Valid @RequestBody GenreDTO genreDTO) {
        Genre updatedGenre = genreMapper.toGenre(genreDTO);
        updatedGenre.setId(genreId);
        return genreMapper.toGenreDTO(genreService.updateGenre(genreId, updatedGenre));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGenre(@PathVariable("id") Long genreId) {
        genreService.deleteGenre(genreId);
    }

}
