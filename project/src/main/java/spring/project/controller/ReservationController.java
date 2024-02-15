package spring.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.project.domain.Reservation;
import spring.project.domain.User;
import spring.project.domain.Workout;
import spring.project.service.ReservationService;
import spring.project.service.UserService;
import spring.project.service.WorkoutService;

import java.util.Optional;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final WorkoutService workoutService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, WorkoutService workoutService){
        this.reservationService = reservationService;
        this.userService = userService;
        this.workoutService = workoutService;
    }

//    @GetMapping("/reservation/{userId}")
//    public String getReservationByUserId(@PathVariable String userId){
//        List<Reservation> reservations = reservationService.findByUserId(userId);
//
//        return "/myPage";
//    }

    @GetMapping("/workout/reservation/{workoutId}")
    public String addReservation(@PathVariable("workoutId") long workoutId, HttpSession session) {
        String userId = (String)session.getAttribute("userId");
        User user = userService.findOne(userId).get(); //User 객체를 받아옴

        System.out.println("Reservation Controller In");
        //reservation 객체 새로 만들기
        Reservation reservation = new Reservation();

        // Workout 객체를 가져오기 위해 WorkoutService를 사용 //workoutId로 운동객체 리스트 받아오기
        Optional<Workout> workout = workoutService.findById(workoutId);

        reservation.setUserId(userId);
        reservation.setWorkoutId(workoutId);
        System.out.println("reservation workoutId = " + workoutId);
        reservation.setTrainerName(workout.get().getTrainerName());
        reservation.setWorkoutName(workout.get().getWorkoutName());
        reservation.setWorkoutType(workout.get().getWorkoutType());
        reservation.setWorkoutDifficulty(workout.get().getWorkoutDifficulty());
        reservation.setStatus("예약완료");

        //새 예약을 등록
        reservationService.add(reservation);

        //list 그냥 리다이렉트하기
        System.out.println("reservation add success!!!!!!");
        return "redirect:/workout/list";
    }

    @RequestMapping("/workout/reservation/delete/{reservationId}")
    public String deleteReservation(@PathVariable("reservationId") long reservationId) {
        System.out.println("ReservationId : "+reservationId);
        reservationService.delete(reservationId);
        return "redirect:/myPage";
    }
}
