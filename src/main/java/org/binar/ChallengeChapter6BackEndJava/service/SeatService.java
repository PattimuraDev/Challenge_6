package org.binar.ChallengeChapter6BackEndJava.service;

import org.binar.ChallengeChapter6BackEndJava.model.Seat;
import org.binar.ChallengeChapter6BackEndJava.model.SeatNumberCompositeKey;
import org.binar.ChallengeChapter6BackEndJava.model.dto.SeatDto;
import java.util.List;

public interface SeatService {
    Seat addSeats(Seat seats);

    void updateSeatsStatus(String newStatus, String nomorBarisKursi, String nomorKolomKursi);

    Seat getSeatById(SeatNumberCompositeKey seatNumberCompositeKey);

    List<Seat> getAllSeatsAvailable();

    void deleteSeats(SeatNumberCompositeKey idSeat);

    Seat seatsDtoMapToEntity(SeatDto seatsDto);
}
