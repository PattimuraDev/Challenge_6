package org.binar.ChallengeChapter6BackEndJava.service;

import org.binar.ChallengeChapter6BackEndJava.model.Seat;
import org.binar.ChallengeChapter6BackEndJava.model.SeatNumberCompositeKey;
import org.binar.ChallengeChapter6BackEndJava.model.dto.SeatDto;
import java.util.List;

/**
 * Interface service untuk menghandle semua permintaan
 * ke repository seat
 * @author Dwi Satria Patra
 */
public interface SeatService {
    Seat addSeats(Seat seats);

    void updateSeatsStatus(String newStatus, String nomorBarisKursi, String nomorKolomKursi);

    Seat getSeatById(SeatNumberCompositeKey seatNumberCompositeKey);

    List<Seat> getAllSeatsAvailable();

    void deleteSeats(SeatNumberCompositeKey idSeat);

    Seat seatsDtoMapToEntity(SeatDto seatsDto);
}
