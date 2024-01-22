package spring.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.project.domain.Exercise;
import spring.project.domain.User;
import spring.project.repository.MemoryUserRepository;
import spring.project.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "redirect:main";
    }
    @GetMapping("/main")
    public String mainPage(Model model){
        List<Exercise> ex = new ArrayList<>();
        ex.add(new Exercise("요가 초급", "요가", "개쩌는강사"));
        ex.add(new Exercise("단체 유산소", "헬스", "별로강사"));
        ex.add(new Exercise("단체 수영", "수영", "착한강사"));

        List<User> tr = new ArrayList<>();
//        tr.add(new User("개쩌는강사"));
//        tr.add(new User("별로강사"));
//        tr.add(new User("착한강사"));

        model.addAttribute("ex", ex);
        model.addAttribute("tr", tr);

        model.addAttribute("userId", model.getAttribute("userId"));

        return "main";
    }
}