package org.codnect.moviebox.service;

import org.codnect.moviebox.dto.GenreDTO;
import org.codnect.moviebox.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreService {

    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> findAll() {
        return null;
    }

    public GenreDTO findGenre(Long genreId) {
        return null;
    }

    public GenreDTO createGenre(GenreDTO genreDTO) {
        return null;
    }

    public GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) {
        return null;
    }

    public void deleteGenre(Long genreId) {

    }

}