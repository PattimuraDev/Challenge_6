package org.binar.ChallengeChapter6BackEndJava.repository;

import org.binar.ChallengeChapter6BackEndJava.model.Seat;
import org.binar.ChallengeChapter6BackEndJava.model.SeatNumberCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatNumberCompositeKey> {
    @Query(value = "update seats set status = ?1 where seats.nomor_baris_kursi = ?2 and seats.nomor_kolom_kursi = ?3", nativeQuery = true)
    @Modifying
    void repoUpdateSeats(
            @Param("status") String newStatus,
            @Param("nomor_baris_kursi") String nomorBarisKursi,
            @Param("nomor_kolom_kursi") String NomorKolomKursi
    );

    @Query(value = "select seats.nomor_baris_kursi, seats.nomor_kolom_kursi, seats.status, seats.studio_name, seats.schedule_id from seats where seats.status = 'available'", nativeQuery = true)
    List<Seat> repoGetAllSeatsAvailable();
}
