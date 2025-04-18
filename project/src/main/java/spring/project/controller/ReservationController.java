package spring.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
        reservation.setTrainerName(workout.get().getTrainer().getName());
        reservation.setWorkoutName(workout.get().getWorkoutName());
        reservation.setWorkoutType(workout.get().getWorkoutType());
        reservation.setWorkoutDifficulty(workout.get().getWorkoutDifficulty());
        reservation.setStatus("완료");

        //새 예약을 등록
        reservationService.add(reservation);

        //list 그냥 리다이렉트하기
        System.out.println("reservation add success!!!!!!");
        return "redirect:/workout/list";
    }

//    @GetMapping("/reservation/{workoutId}")
//    public String addReservation(@PathVariable long workoutId, HttpServletRequest request) {
//        System.out.println("Reservation Controller IN");
//        String userId = (String)request.getSession().getAttribute("userId");
//
//        User user = userService.findOne(userId).get();
//
//        //reservation 객체 만들어서 user정보 가져와서 set 한 다음에
//        Reservation reservation = new Reservation();
//        reservation.setUserId(user.getId());
//
//        // Workout 객체를 가져오기 위해 WorkoutService를 사용
//        Workout workout = workoutService.findById(workoutId);
//        reservation.setWorkoutId(workout.getWorkoutId());
//        reservation.setStatus("진행중");
//
//        System.out.println("reservationId : " +reservation.getReservationId() );
//        System.out.println("userID : " +reservation.getUserId());
//        System.out.println("status : " +reservation.getStatus() );
//        System.out.println("reservationId : " +reservation.getReservationId());
//
//        //새 예약을 등록
//        reservationService.add(reservation);
//        System.out.println("reservation add success!!!!!!");
//        return "redirect:/workout/list";
//    }

//    @PutMapping("/reservation/update")
//    public Reservation updateReservation(@RequestBody Reservation reservation) {
//        return reservationService.update(reservation);
//    }

//    @DeleteMapping("/reservation/{reservationId}")
//    public void deleteReservation(@PathVariable Long reservationId) {
//        reservationService.delete(reservationId);
//    }
}
