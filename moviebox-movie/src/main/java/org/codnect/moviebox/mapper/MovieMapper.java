package org.codnect.moviebox.mapper;

import org.codnect.moviebox.dto.MovieDTO;
import org.codnect.moviebox.model.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { GenreMapper.class })
public interface MovieMapper {

    MovieDTO toMovieDTO(Movie Movie);

    Movie toMovie(MovieDTO movieDTO);

    List<MovieDTO> toMovieDTOList(List<Movie> movies);

    List<Movie> toMovieList(List<MovieDTO> movieDTOList);


}