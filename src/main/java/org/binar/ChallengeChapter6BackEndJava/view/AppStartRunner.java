//package org.binar.ChallengeChapter6BackEndJava.view;
//
//import org.binar.ChallengeChapter6BackEndJava.model.Film;
//import org.binar.ChallengeChapter6BackEndJava.model.Schedule;
//import org.binar.ChallengeChapter6BackEndJava.model.Seat;
//import org.binar.ChallengeChapter6BackEndJava.model.SeatNumberCompositeKey;
//import org.binar.ChallengeChapter6BackEndJava.service.FilmServiceImpl;
//import org.binar.ChallengeChapter6BackEndJava.service.ScheduleServiceImpl;
//import org.binar.ChallengeChapter6BackEndJava.service.SeatServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//@Profile("!test")
//@Component
//public class AppStartRunner implements CommandLineRunner {
//    @Autowired
//    FilmServiceImpl filmService;
//
//    @Autowired
//    SeatServiceImpl seatService;
//
//    @Autowired
//    ScheduleServiceImpl scheduleService;
//
//    /**
//     * running console
//     *
//     * @param args arguments
//     */
//    @Override
//    public void run(String... args) {
//        //initializeDb(); // only run once
//        consoleRunner();
//    }
//
//    /**
//     * Fungsi untuk menjalankan console
//     */
//    private void consoleRunner() {
//        boolean terminalRunning = true;
//        int pilihan;
//        Scanner input = new Scanner(System.in);
//
//        while (terminalRunning) {
//            mainMenu();
//            System.out.print("Pilihan: ");
//            pilihan = input.nextInt();
//            switch (pilihan) {
//                case 1:
//                    List<Film> listFilmIsPlaying = filmService.getFilmIsPlaying();
//                    for (Film film : listFilmIsPlaying) {
//                        System.out.println(film.getFilmName());
//                    }
//                    System.out.println();
//                    break;
//                case 2:
//                    input.nextLine();
//                    System.out.print("Masukkan judul film: ");
//                    String inputJudul = input.nextLine();
//                    List<Schedule> listSchedules = scheduleService.schedulesOfFilmsByName(inputJudul);
//                    for (Schedule schedule : listSchedules) {
//                        System.out.println("Jadwal tayang: " +
//                                schedule.getDate() +
//                                " " +
//                                schedule.getStartTime() +
//                                "-" +
//                                schedule.getEndTime() +
//                                "\nStudio: " +
//                                schedule.getStudioName() +
//                                "\nHarga tiket: " +
//                                schedule.getTicketPrice() +
//                                "\n"
//                        );
//                    }
//                    break;
//                case 3:
//                    List<Seat> seatsAvailable = seatService.getAllSeatsAvailable();
//                    List<Schedule> listSchedule = new ArrayList<>();
//                    List<Film> listFilm = new ArrayList<>();
//                    for (int i = 0; i < seatsAvailable.size(); i++) {
//                        listSchedule.add(i, scheduleService.findScheduleById(seatsAvailable.get(i).getScheduleID()));
//                    }
//
//                    for (int i = 0; i < listSchedule.size(); i++) {
//                        listFilm.add(i, filmService.getFilmById(listSchedule.get(i).getFilmCode()));
//                    }
//
//                    for (int i = 0; i < seatsAvailable.size(); i++) {
//                        System.out.println("Nomor seat: " +
//                                seatsAvailable.get(i).getSeatNumberCompositeKey().getNomorBarisKursi() +
//                                seatsAvailable.get(i).getSeatNumberCompositeKey().getNomorKolomKursi() +
//                                "\nStatus: " +
//                                seatsAvailable.get(i).getStatus() +
//                                "\nStudio: " +
//                                seatsAvailable.get(i).getStudioName() +
//                                "\nJudul film: " +
//                                listFilm.get(i).getFilmName() +
//                                "\nJadwal tayang: " +
//                                listSchedule.get(i).getDate() +
//                                " " +
//                                listSchedule.get(i).getStartTime() +
//                                "-" +
//                                listSchedule.get(i).getEndTime() +
//                                "\n"
//                        );
//                    }
//                    System.out.println();
//                    break;
//                case 4:
//                    input.nextLine();
//                    System.out.print("Masukkan nomor baris kursi: ");
//                    String inputNomorBarisKursi = input.nextLine();
//                    System.out.print("Masukkan nomor kolom kursi: ");
//                    String inputNomorKolomKursi = input.nextLine();
//                    seatService.updateSeatsStatus(
//                            "not available",
//                            inputNomorBarisKursi,
//                            inputNomorKolomKursi
//                    );
//                    break;
//                case 0:
//                    terminalRunning = false;
//                    break;
//                default:
//                    break;
//            }
//        }
//        System.exit(0);
//    }
//
//    /**
//     * Fungsi untuk menampilkan main menu
//     */
//    private void mainMenu() {
//        System.out.println("=====\n" +
//                "Main Menu\n" +
//                "=====\n" +
//                "1. Film ditayangkan saat ini\n" +
//                "2. Jadwal film, studio, harga tiket\n" +
//                "3. Seats tersedia\n" +
//                "4. Reservase tiket\n" +
//                "0. Akhiri aplikasi\n" +
//                "=====\n"
//        );
//    }
//
//    /**
//     * Fungsi untuk menginisialisasi database di awal program dijalankan (dijalankan sekali saja)
//     */
//    private void initializeDb() {
//        //initialize film and schedules table
//        Film film = new Film();
//        film.setFilmName("Ini judul film 1");
//        film.setFilmCode(null);
//        film.setIsPlaying(true);
//        filmService.addFilms(film);
//
//        List<Schedule> schedulesListOfFilms = new ArrayList<>();
//        schedulesListOfFilms.add(new Schedule(null, film.getFilmCode(), "11 Juli", "19.00", "21.00", 20000L, "Studio B"));
//        schedulesListOfFilms.add(new Schedule(null, film.getFilmCode(), "13 Juli", "15.00", "17.00", 15000L, "Studio D"));
//        film.setSchedulesList(schedulesListOfFilms);
//        filmService.addFilms(film);
//
//        Film film2 = new Film();
//        film2.setFilmName("Ini judul film 2");
//        film2.setFilmCode(null);
//        film2.setIsPlaying(false);
//        filmService.addFilms(film2);
//
//        List<Schedule> schedulesListOfFilms2 = new ArrayList<>();
//        schedulesListOfFilms2.add(new Schedule(null, film2.getFilmCode(), "12 Juli", "14.00", "17.00", 35000L, "Studio E"));
//        schedulesListOfFilms2.add(new Schedule(null, film2.getFilmCode(), "17 Juli", "16.00", "19.00", 25000L, "Studio C"));
//        film2.setSchedulesList(schedulesListOfFilms2);
//        filmService.addFilms(film2);
//
//        Film film3 = new Film();
//        film3.setFilmName("Ini judul film 3");
//        film3.setFilmCode(null);
//        film3.setIsPlaying(true);
//        filmService.addFilms(film3);
//
//        List<Schedule> schedulesListOfFilms3 = new ArrayList<>();
//        schedulesListOfFilms3.add(new Schedule(null, film3.getFilmCode(), "20 Juli", "20.00", "23.00", 35000L, "Studio A"));
//        schedulesListOfFilms3.add(new Schedule(null, film3.getFilmCode(), "21 Juli", "09.00", "12.00", 25000L, "Studio C"));
//        film3.setSchedulesList(schedulesListOfFilms3);
//        filmService.addFilms(film3);
//
//        //initialize seats table
//        Seat seat1 = new Seat(new SeatNumberCompositeKey("1", "A"), "Studio A", "available", 1L);
//        Seat seat2 = new Seat(new SeatNumberCompositeKey("1", "B"), "Studio A", "available", 1L);
//        Seat seat3 = new Seat(new SeatNumberCompositeKey("2", "A"), "Studio B", "available", 2L);
//        Seat seat4 = new Seat(new SeatNumberCompositeKey("2", "B"), "Studio B", "available", 2L);
//        Seat seat5 = new Seat(new SeatNumberCompositeKey("3", "A"), "Studio C", "available", 2L);
//        Seat seat6 = new Seat(new SeatNumberCompositeKey("3", "B"), "Studio C", "available", 3L);
//        Seat seat7 = new Seat(new SeatNumberCompositeKey("4", "A"), "Studio D", "available", 3L);
//        Seat seat8 = new Seat(new SeatNumberCompositeKey("4", "B"), "Studio D", "available", 3L);
//        Seat seat9 = new Seat(new SeatNumberCompositeKey("5", "A"), "Studio E", "available", 3L);
//        Seat seat10 = new Seat(new SeatNumberCompositeKey("5", "B"), "Studio E", "available", 2L);
//
//        seatService.addSeats(seat1);
//        seatService.addSeats(seat2);
//        seatService.addSeats(seat3);
//        seatService.addSeats(seat4);
//        seatService.addSeats(seat5);
//        seatService.addSeats(seat6);
//        seatService.addSeats(seat7);
//        seatService.addSeats(seat8);
//        seatService.addSeats(seat9);
//        seatService.addSeats(seat10);
//    }
//}
