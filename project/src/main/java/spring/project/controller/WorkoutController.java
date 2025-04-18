package spring.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
//import spring.project.domain.Reservation;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import spring.project.domain.User;
import spring.project.domain.Workout;
import spring.project.service.UserService;
import spring.project.service.WorkoutService;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    @Autowired
    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    //전체 Workout 리스트를 가져와서 "list"뷰에 전달하는 메소드
    @RequestMapping("/workout/list")
    public String getAllWorkoutList(Model model) {
        List<Workout> workoutList = workoutService.getAllWorkoutList();
        model.addAttribute("workoutList", workoutList);
        return "workout/workoutList";

    }

    //운동 추가 폼으로 이동하는 메소드 // 됨
    @GetMapping("/workout/add")
    public String addWorkoutForm() {
        return "/workout/addWorkoutForm";  // addWorkoutForm은 추가 폼을 나타내는 뷰 이름
    }

    //운동 추가 하기 , 됨
    @PostMapping("/workout/add")
    public String addWorkout(Workout workout, HttpServletRequest request) {
        String trainerId = (String) request.getSession().getAttribute("userId");
        Optional<User> user = userService.findOne(trainerId);

        workout.setTrainer(User.builder().id(trainerId).build());

        //workoutService를 사용하여 workout에 추가
        workoutService.addWorkout(workout);
        System.out.println("Controller- workoutId가 " + workout.getWorkoutId());
        return "redirect:/workout/list";
    }

    // 운동 삭제 하기
    @RequestMapping("/workout/delete/{workoutId}")
    public String deleteWorkout(@PathVariable long workoutId) {
        System.out.println("delete Controller in");
        workoutService.deleteWorkout(workoutId);
        return "redirect:/workout/list";
    }


//    //운동 신청하기
//    @PostMapping("/reservation")
//    public String reserveWorkout(Workout workout, HttpServletRequest request) {
//        String userId = (String)request.getSession().getAttribute("userId");
//
//        int num = 0;
//        Reservation reservation = new Reservation();
//        reservation.setReservationId((Long)num++);
//        reservation.setUser(userId);
//
//
//        //WorkoutService를 사용하여 운동을 신청
//        workoutService.reserveWorkout(workoutId);
//        return "redirect:/workout/list";
    //  }

    //해당 keyword로 검색하기
    @GetMapping("/workout/search")
    public String searchWorkout(@RequestParam("workoutDifficulty") String workoutDifficulty,
                                @RequestParam("workoutType") String workoutType, Model model) {
        //keyword를 사용하여 검색한 리스트를 반환
        System.out.println(workoutDifficulty);
        System.out.println(workoutType);
        List<Workout> workoutList = workoutService.findByKeyword(workoutDifficulty, workoutType);

        //System.out.println(workoutList.get(0).getWorkoutType());
        model.addAttribute("workoutList", workoutList);
        return "/workout/workoutList";
    }

}
