package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import spring.project.domain.Workout;
import spring.project.service.WorkoutService;

import java.util.List;

@Controller
//@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    //전체 Workout 리스트를 가져와서 "list"뷰에 전달하는 메소드
    @RequestMapping("/workout/list")
    public String getAllWorkoutList(Model model){
        List<Workout> workoutList = workoutService.getAllWorkoutList();

        if(workoutList.isEmpty()){ //운동 리스트가 비어있으면 에러페이지로
            return "/workout/workoutError";
        }
        else{
            model.addAttribute("workoutList",workoutList);
            return "/workout/workoutList";
        }
    }

    //운동 추가 폼으로 이동하는 메소드 // 됨
    @GetMapping("/workout/add")
    public String addWorkoutForm() {
        return "/workout/addWorkoutForm";  // addWorkoutForm은 추가 폼을 나타내는 뷰 이름
    }

    //운동 추가 하기 , 됨
    @PostMapping("/workout/add")
    public String addWorkout(Workout workout){
        //workoutService를 사용하여 workout에 추가
        workoutService.addWorkout(workout);
        return "redirect:/workout/list";
    }

    //운동 수정 하기
    @PostMapping("/workout/update")
    public String updateWorkout(@RequestParam ("workoutId") int workoutId, Workout updatedWorkout){
        //workoutService를 사용하여 workout을 업데이트
        workoutService.updateWorkout(workoutId, updatedWorkout);
        return "redirect:/workout/list";
    }

    //운동 삭제 하기
    @DeleteMapping("/workout/delete/{workoutId}")
    public String deleteWorkout(@PathVariable int workoutId){
        //workoutService를 사용하여 workout을 삭제
        workoutService.deleteWorkout(workoutId);
        return "redirect:/workout/list";
    }

//    //운동 신청하기
//    @PostMapping("/workout/reservation")
//    public String reserveWorkout(@RequestParam("workoutId") int workoutId) {
//        //WorkoutService를 사용하여 운동을 신청
//        workoutService.reserveWorkout(workoutId);
//        return "redirect:/workout/list";
//    }

    //해당 keyword로 검색하기
    @GetMapping("/workout/search")
    public String searchWorkout(@RequestParam ("workoutDifficulty") String workoutDifficulty,
                                @RequestParam ("workoutType") String workoutType, Model model) {
        //keyword를 사용하여 검색한 리스트를 반환
        System.out.println(workoutDifficulty);
        System.out.println(workoutType);
        List<Workout> workoutList = workoutService.findByKeyword(workoutDifficulty, workoutType);

        System.out.println(workoutList.get(0).getWorkoutType());
        model.addAttribute("workoutList",workoutList);
        return "/workout/workoutList";
    }
}
