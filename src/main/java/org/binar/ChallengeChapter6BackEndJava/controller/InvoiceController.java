package org.binar.ChallengeChapter6BackEndJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.binar.ChallengeChapter6BackEndJava.model.Film;
import org.binar.ChallengeChapter6BackEndJava.model.Schedule;
import org.binar.ChallengeChapter6BackEndJava.model.Seat;
import org.binar.ChallengeChapter6BackEndJava.model.SeatNumberCompositeKey;
import org.binar.ChallengeChapter6BackEndJava.service.FilmServiceImpl;
import org.binar.ChallengeChapter6BackEndJava.service.ScheduleServiceImpl;
import org.binar.ChallengeChapter6BackEndJava.service.SeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Tag(name = "INVOICE")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    SeatServiceImpl seatService;

    @Autowired
    ScheduleServiceImpl scheduleService;

    @Autowired
    FilmServiceImpl filmService;

    /**
     * Method controller untuk mengakomodasi kebutuhan generate invoice dalam bentuk file pdf
     * @param nomorBarisKursi parameter untuk nomor baris kursi yang dipesan
     * @param nomorKolomKursi parameter untuk nomor kolom kursi yang dipesan
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menampilkan hasil generate invoice dalam pdf")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(
            responseCode = "200",
            description = "Generate berhasil",
            content = @Content(mediaType = "application/pdf")
    )
    @GetMapping("/cetak_invoice")
    public ResponseEntity<byte[]> generateInvoice(
            @RequestParam("nomor_baris_kursi") String nomorBarisKursi,
            @RequestParam("nomor_kolom_kursi") String nomorKolomKursi
    ){
        try{
            List<Map<String, Object>> dataTiket = new ArrayList<>();
            Map<String, Object> tiket = new HashMap<>();

            // retrive all data needed
            Seat seats = seatService.getSeatById(new SeatNumberCompositeKey(
                    nomorBarisKursi,
                    nomorKolomKursi
            ));
            String seatNumber = "Nomor kursi: " +
                    seats.getSeatNumberCompositeKey().getNomorBarisKursi() +
                    seats.getSeatNumberCompositeKey().getNomorKolomKursi();

            Schedule schedule = scheduleService.findScheduleById(seats.getScheduleID());
            String jadwalFilm = "Jadwal tayang: " +
                    schedule.getDate() +
                    " pukul " +
                    schedule.getStartTime() +
                    "-" +
                    schedule.getEndTime();

            Film films = filmService.getFilmById(schedule.getFilmCode());
            String namaFilm = "Judul film: " + films.getFilmName();

            tiket.put("nomorKursi", seatNumber);
            tiket.put("namaFilm", namaFilm);
            tiket.put("jadwalFilm", jadwalFilm);
            dataTiket.add(tiket);

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(dataTiket);
            Map<String, Object> parameter = new HashMap<>();

            JasperReport compileReport = JasperCompileManager
                    .compileReport(Files.newInputStream(Paths.get("src/main/resources/invoice.jrxml")));
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    compileReport,
                    parameter,
                    beanCollectionDataSource
            );
            byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
            System.err.println(data);

            HttpHeaders headers = new HttpHeaders();
            headers.add(
                    "Content-Disposition",
                    "attachment; filename=invoice.pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
        }catch (Exception e){
            return null;
        }
    }
}
