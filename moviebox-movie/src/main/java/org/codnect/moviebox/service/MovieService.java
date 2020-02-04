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
    private MovieMapper movieMapper;
    private MovieEventPublisher movieEventPublisher;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper, MovieEventPublisher movieEventPublisher) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.movieEventPublisher = movieEventPublisher;
    }

    public List<MovieDTO> findAllMovies(Pageable pageable) {
        return movieMapper.toMovieDTOList(movieRepository.findAllMovies(pageable));
    }

    public MovieDTO findMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        return movieMapper.toMovieDTO(
                optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId))
        );
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = movieMapper.toMovie(movieDTO);
        movieDTO.setId(movie.getId());
        movieEventPublisher.publish(new MovieCreatedEvent(movie.getId(), movieDTO));
        return movieDTO;
    }

    public MovieDTO updateMovie(Long movieId, MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        Movie updatedMovie = movieMapper.toMovie(movieDTO);
        updatedMovie.setId(movieId);
        return movieMapper.toMovieDTO(movieRepository.save(movieRepository.save(updatedMovie)));
    }

    public void deleteMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        movieRepository.delete(movie);
    }

}
