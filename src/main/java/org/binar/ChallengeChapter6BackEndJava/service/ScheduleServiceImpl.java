package org.binar.ChallengeChapter6BackEndJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.ChallengeChapter6BackEndJava.model.Film;
import org.binar.ChallengeChapter6BackEndJava.model.Schedule;
import org.binar.ChallengeChapter6BackEndJava.model.dto.ScheduleDto;
import org.binar.ChallengeChapter6BackEndJava.repository.FilmRepository;
import org.binar.ChallengeChapter6BackEndJava.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    FilmRepository filmRepository;
    //mapper untuk keperluan pemetaan objek
    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method yang digunakan untuk mengambil semua jadwal tersedia untuk film dengan judul tertentu
     *
     * @param filmName parameter nama/judul film
     * @return list schedule film terkait
     */
    @Override
    public List<Schedule> schedulesOfFilmsByName(String filmName) {
        List<Schedule> listOfAllSchedules = scheduleRepository.findAll();
        List<Schedule> result = new ArrayList<>();
        for (Schedule schedules : listOfAllSchedules) {
            if (Objects.equals(schedules.getFilmCode(), findFilmIdByName(filmName))) {
                result.add(schedules);
            }
        }
        return result;
    }

    /**
     * Method untuk mendapatkan schedule berdasarkan id-nya
     *
     * @param idSchedule parameter untuk id dari schedule yang dimaksud
     * @return objek schedule hasil pencarian berdasarkan id-nya
     */
    @Override
    public Schedule findScheduleById(Long idSchedule) {
        if (scheduleRepository.findById(idSchedule).isPresent()) {
            return scheduleRepository.findById(idSchedule).get();
        } else {
            return null;
        }
    }

    /**
     * Method yang digunakna untuk mengupdate kode film pada table jadwal/schedule
     *
     * @param scheduleId parameter untuk id dari schedule yang dimaksud
     * @param filmId     parameter untuk id film yang ingin dimasukkan
     */
    @Override
    public void updateFilmCodeOfSchedules(Long scheduleId, Long filmId) {
        scheduleRepository.repoUpdateFilmsCodeOfSchedules(filmId, scheduleId);
    }

    /**
     * Method yang digunakan untuk menambahkan jadwal/schedule baru
     *
     * @param schedule parameter untuk objek schedule
     * @return objek schedule yang berhasil tersimpan
     */
    @Override
    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    /**
     * Method yang digunakan untuk menghapus schedule film berdasarkan id dari schedule
     *
     * @param idSchedule parameter untuk id schedule yang dimaksud
     */
    @Override
    public void deleteSchedule(Long idSchedule) {
        scheduleRepository.deleteById(idSchedule);
    }

    /**
     * Method yang digunakan untuk menghapus schedule film berdasarkan id film
     *
     * @param filmId parameter untuk id dari film
     */
    @Override
    public void deleteSchedulesByFilmId(Long filmId) {
        scheduleRepository.repoDeleteScheduleByFilmId(filmId);
    }

    /**
     * Method yang digunakan untuk memetakan objek SchedulesDto menjadi Schedules
     *
     * @param scheduleDto parameter untuk objek SchedulesDto
     * @return hasil pemetaan menjadi objek Schedules
     */
    @Override
    public Schedule scheduleDtoMapToEntity(ScheduleDto scheduleDto) {
        return mapper.convertValue(scheduleDto, Schedule.class);
    }


    /**
     * Method uang digunakan untuk mencari id film berdasarkan nama/judulnya
     *
     * @param filmName parameter untuk nama dari film
     * @return hasil pencarian id film
     */
    public Long findFilmIdByName(String filmName) {
        List<Film> listOfAllFilms = filmRepository.findAll();
        Long filmCode = 0L;
        for (Film films : listOfAllFilms) {
            if (Objects.equals(films.getFilmName(), filmName)) {
                filmCode = films.getFilmCode();
            }
        }
        return filmCode;
    }
}
