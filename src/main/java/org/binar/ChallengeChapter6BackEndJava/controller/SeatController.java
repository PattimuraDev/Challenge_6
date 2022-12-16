package org.binar.ChallengeChapter6BackEndJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.ChallengeChapter6BackEndJava.model.Seat;
import org.binar.ChallengeChapter6BackEndJava.model.SeatNumberCompositeKey;
import org.binar.ChallengeChapter6BackEndJava.model.dto.SeatDto;
import org.binar.ChallengeChapter6BackEndJava.model.response.CustomResponseJson;
import org.binar.ChallengeChapter6BackEndJava.service.SeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Kelas controller untuk menghandle endpoint terkait schedule
 * @author Dwi Satria Patra
 */
@Tag(name = "SEATS")
@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    SeatServiceImpl seatsService;

    /**
     * Method controller untuk mengakomodasi kebutuhan menambah data seat baru
     * @param seatDto parameter data transfer object untuk seat
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menambahkan seat baru")
    @PreAuthorize("hasAuthority('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(
            responseCode = "201",
            description = "Seat baru berhasil ditambahkan",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SeatDto.class)
            )
    )
    @PostMapping("/create_seat")
    public ResponseEntity<Seat> createSeats(@RequestBody SeatDto seatDto) {
        final Seat result = seatsService.addSeats(seatsService.seatsDtoMapToEntity(seatDto));
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan update data seat
     * @param newStatus parameter untuk memasukkan status seat sekarang (available/not available)
     * @param nomorBarisKursi parameter untuk salah satu composite key, yaitu nomor baris seat
     * @param nomorKolomKursi parameter untuk salah satu composite key, yaitu nomor kolom seat
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk mengupdate data seat")
    @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(
            responseCode = "200",
            description = "Seat berhasil diupdate",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomResponseJson.class)
            )
    )
    @PutMapping("/update_seat_status")
    public ResponseEntity<CustomResponseJson> updateSeatsStatus(
            @RequestParam String newStatus,
            @RequestParam String nomorBarisKursi,
            @RequestParam String nomorKolomKursi
    ){
        try{
            seatsService.updateSeatsStatus(newStatus, nomorBarisKursi, nomorKolomKursi);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Seat status berhasil diupdate",
                            "200"
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Seat status gagal diupdate",
                            "500"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan menghapus data seat
     * @param idSeat parameter untuk object composite key dari seat
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menghapus data seat")
    @PreAuthorize("hasAuthority('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(
            responseCode = "200",
            description = "Seat berhasil dihapus",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomResponseJson.class)
            )
    )
    @DeleteMapping("/delete_seat")
    public ResponseEntity<CustomResponseJson> deleteSeat(@RequestBody SeatNumberCompositeKey idSeat){
        try {
            seatsService.deleteSeats(idSeat);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Seat berhasil dihapus",
                            "200"
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Operasi menghapus seat gagal",
                            "500"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
