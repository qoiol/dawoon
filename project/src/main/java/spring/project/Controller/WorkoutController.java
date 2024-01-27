package spring.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.project.domain.Workout;
import spring.project.service.WorkoutService;

import java.util.List;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    //전체 리스트 가져오기
    @RequestMapping("/list")
    public String getWorkoutList(Model model){
        List<Workout> workoutList = workoutService.getAllWorkoutList();
        model.addAttribute("workoutList",workoutList);
        return "list";
    }

    //운동 추가 하기 (폼으로 이동?)
    @PostMapping("/add.do")
    public String uploadWorkout(Workout workout){
        //workoutService를 사용하여 workout에 추가
        workoutService.addWorkout(workout);
        return "redirect:/workout/list";
    }

    //운동 수정 하기
    @PostMapping("/update.do")
    public String updateWorkout(Workout workout){
        //workoutService를 사용하여 workout을 업데이트
        workoutService.updateWorkout(workout);
        return "redirect:/workout/list";
    }

    //운동 삭제 하기
    @RequestMapping("/delete")
    public String deleteWorkout(int workoutId){
        workoutService.deleteWorkout(workoutId);
        return "redirect:/workout/list";
    }

    //운동 신청하기
    @RequestMapping("/reservation")
    public String reservation(){
        return "";
    }

}
