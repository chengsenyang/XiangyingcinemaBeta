package com.example.xiangyingcinema.dao;

import com.example.xiangyingcinema.model.film.FilmT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmTRepository extends JpaRepository<FilmT,Integer> {
    FilmT findByUuid(Integer filmId);
}
