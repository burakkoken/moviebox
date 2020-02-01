package org.codnect.moviebox.service;

import org.codnect.moviebox.dto.MovieDTO;
import org.codnect.moviebox.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findAllMovies(Pageable pageable) {
        return null;
    }

    public MovieDTO findMovie(Long movieId) {
        return null;
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        return null;
    }

    public MovieDTO updateMovie(Long movieId, MovieDTO movieDTO) {
        return null;
    }

    public void deleteMovie(Long movieId) {

    }

}
