package org.codnect.moviebox.service;

import org.codnect.moviebox.dto.GenreDTO;
import org.codnect.moviebox.exception.GenreDuplicateException;
import org.codnect.moviebox.exception.GenreNotFoundException;
import org.codnect.moviebox.mapper.GenreMapper;
import org.codnect.moviebox.model.Genre;
import org.codnect.moviebox.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreService {

    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findGenre(Long genreId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        return optionalGenre.orElseThrow(() -> new GenreNotFoundException(genreId));
    }

    public Genre createGenre(Genre genre) {
        Optional<Genre> optionalGenre = genreRepository.findByName(genre.getName());
        if(optionalGenre.isPresent()) {
            throw new GenreDuplicateException(genre.getName());
        }
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long genreId, Genre genre) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        optionalGenre.orElseThrow(() -> new GenreNotFoundException(genreId));
        optionalGenre = genreRepository.findByName(genre.getName());
        if(optionalGenre.isPresent()) {
            throw new GenreDuplicateException(genre.getName());
        }
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long genreId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        Genre genre = optionalGenre.orElseThrow(() -> new GenreNotFoundException(genreId));
        genreRepository.delete(genre);
    }

}