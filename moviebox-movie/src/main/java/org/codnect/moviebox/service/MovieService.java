package org.codnect.moviebox.service;

import org.codnect.moviebox.dto.MovieDTO;
import org.codnect.moviebox.event.movie.MovieCreatedEvent;
import org.codnect.moviebox.event.movie.MovieEventPublisher;
import org.codnect.moviebox.exception.MovieNotFoundException;
import org.codnect.moviebox.mapper.MovieMapper;
import org.codnect.moviebox.model.Movie;
import org.codnect.moviebox.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Cacheable(cacheNames = "movies")
    public List<Movie> findAllMovies(Pageable pageable) {
        return movieRepository.findAllMovies(pageable);
    }

    @Cacheable(cacheNames = "movie", key = "'movie#' + #movieId")
    public Movie findMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        return optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
    }

    @CachePut(cacheNames = "movie", key = "'movie#' + #movie.id")
    @CacheEvict(cacheNames = "movies", allEntries = true)
    public Movie createMovie(Movie movie) {
        Movie newMovie = movieRepository.save(movie);
        movieEventPublisher.publish(MovieCreatedEvent.of(newMovie.getId(), newMovie));
        return newMovie;
    }

    @CachePut(cacheNames = "movie", key = "'movie#' + #movie.id")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "movie", condition = "#key.endsWith(#movie.id)"),
                    @CacheEvict(cacheNames = "movies", allEntries = true),
            }
    )
    public Movie updateMovie(Long movieId, Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        return movieRepository.save(movie);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "movie", condition = "#key.endsWith(#movie.id)"),
                    @CacheEvict(cacheNames = "movies", allEntries = true),
            }
    )
    public void deleteMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        movieRepository.delete(movie);
    }

}
