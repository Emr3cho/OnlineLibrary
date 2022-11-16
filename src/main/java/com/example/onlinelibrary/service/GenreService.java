package com.example.onlinelibrary.service;

import com.example.onlinelibrary.models.Genre;
import com.example.onlinelibrary.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> allGenres(){
        return genreRepository.findAll();
    }
}
