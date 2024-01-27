package spring.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

    //전체 리스트 가져오기
    @RequestMapping("/list")
    public ModelAndView getWorkoutList(){

        // mav 객체에 운동 리스트 전체 가져와서 뷰로 전달.
        ModelAndView mav = new ModelAndView();


        return mav;
    }

    //운동 추가 하기

    //운동 수정 하기

    //운동 삭제 하기

    //운동 신청하기

    //
}
