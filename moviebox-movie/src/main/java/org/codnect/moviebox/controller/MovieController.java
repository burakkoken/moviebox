package org.codnect.moviebox.controller;

import org.codnect.moviebox.dto.MovieDTO;
import org.codnect.moviebox.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDTO> findAllMovies(
            @PageableDefault(
                    page = 0, size = 20, sort = {"title"}, direction = Sort.Direction.ASC
            ) Pageable pageable) {
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDTO findMovie(@PathVariable("id") Long movieId) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDTO updateMovie(@PathVariable("id") Long movieId, @Valid @RequestBody MovieDTO movieDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable("id") Long movieId) {

    }

}
