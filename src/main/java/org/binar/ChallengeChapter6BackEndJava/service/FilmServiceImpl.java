package org.binar.ChallengeChapter6BackEndJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.ChallengeChapter6BackEndJava.model.Film;
import org.binar.ChallengeChapter6BackEndJava.model.dto.FilmDto;
import org.binar.ChallengeChapter6BackEndJava.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepository filmRepository;
    // mapper untuk keperluan pemetaan objek
    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method yang digunakan untuk add film
     *
     * @param film paramater berisi data object film
     * @return film hasil save
     */
    @Override
    public Film addFilms(Film film) {
        return filmRepository.save(film);
    }

    /**
     * Method yang digunakan untuk mengupdate nama/judul film
     *
     * @param oldFilmName parameter untuk nama/judul awal film
     * @param newFilmName parameter untuk nama/judul film yang baru
     */
    @Override
    public void updateFilmByName(String oldFilmName, String newFilmName) {
        filmRepository.repoUpdateFilmByName(oldFilmName, newFilmName);
    }

    /**
     * Method untuk mendapatkan film berdasarkan id film
     *
     * @param idFilm parameter untuk id dari film
     * @return film hasil pencarian berdasarkan id
     */
    @Override
    public Film getFilmById(Long idFilm) {
        if (filmRepository.findById(idFilm).isPresent()) {
            return filmRepository.findById(idFilm).get();
        } else {
            return null;
        }
    }

    /**
     * Method yang digunakan untuk menghapus film berdasarkan id dari film
     *
     * @param filmId parameter untuk id dari film
     */
    @Override
    public void deleteFilmById(Long filmId) {
        filmRepository.deleteById(filmId);
    }

    /**
     * Method untuk mengambil semua film dengan status tayang saat ini
     *
     * @return semua film yang sedang tayang (list)
     */
    @Override
    public List<Film> getFilmIsPlaying() {
        return filmRepository.repoGetFilmIsPlaying();
    }

    /**
     * Method untuk melakukan pemetaan dari objek FilmsDto menjadi films
     *
     * @param filmDto parameter untuk objek FilmsDto
     * @return hasil pemetaan menjadi objek Films
     */
    @Override
    public Film filmDtoMapToEntity(FilmDto filmDto) {
        return mapper.convertValue(filmDto, Film.class);
    }
}
