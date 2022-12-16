package org.binar.ChallengeChapter6BackEndJava.service;

import org.binar.ChallengeChapter6BackEndJava.model.Schedule;
import org.binar.ChallengeChapter6BackEndJava.model.dto.ScheduleDto;
import java.util.List;

/**
 * Interface service untuk menghandle semua permintaan
 * ke repository schedule
 * @author Dwi Satria Patra
 */
public interface ScheduleService {
    List<Schedule> schedulesOfFilmsByName(String filmName);

    Schedule findScheduleById(Long idSchedule);

    void updateFilmCodeOfSchedules(Long schedulesId, Long filmId);

    Schedule addSchedule(Schedule schedules);

    void deleteSchedule(Long idSchedule);

    void deleteSchedulesByFilmId(Long filmId);

    Schedule scheduleDtoMapToEntity(ScheduleDto schedulesDto);
}
