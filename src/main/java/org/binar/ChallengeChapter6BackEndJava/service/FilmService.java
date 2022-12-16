package org.binar.ChallengeChapter6BackEndJava.service;

import org.binar.ChallengeChapter6BackEndJava.model.Film;
import org.binar.ChallengeChapter6BackEndJava.model.dto.FilmDto;
import java.util.List;

/**
 * Interface service untuk menghandle semua permintaan
 * ke repository user
 * @author Dwi Satria Patra
 */
public interface FilmService {
    Film addFilms(Film films);

    void updateFilmByName(String oldFilmName, String newFilmName);

    Film getFilmById(Long idFilm);

    void deleteFilmById(Long idFilm);

    List<Film> getFilmIsPlaying();

    Film filmDtoMapToEntity(FilmDto filmsDto);
}
