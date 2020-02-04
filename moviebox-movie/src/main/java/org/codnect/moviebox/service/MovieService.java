package org.codnect.moviebox.service;

import org.codnect.moviebox.dto.MovieDTO;
import org.codnect.moviebox.event.movie.MovieCreatedEvent;
import org.codnect.moviebox.event.movie.MovieEventPublisher;
import org.codnect.moviebox.exception.MovieNotFoundException;
import org.codnect.moviebox.mapper.MovieMapper;
import org.codnect.moviebox.model.Movie;
import org.codnect.moviebox.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private MovieRepository movieRepository;
    private MovieEventPublisher movieEventPublisher;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieEventPublisher movieEventPublisher) {
        this.movieRepository = movieRepository;
        this.movieEventPublisher = movieEventPublisher;
    }

    public List<Movie> findAllMovies(Pageable pageable) {
        return movieRepository.findAllMovies(pageable);
    }

    public Movie findMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        return optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
    }

    public Movie createMovie(Movie movie) {
        Movie newMovie = movieRepository.save(movie);
        movieEventPublisher.publish(new MovieCreatedEvent(movie.getId(), newMovie));
        return newMovie;
    }

    public Movie updateMovie(Long movieId, Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        movieRepository.delete(movie);
    }

}
