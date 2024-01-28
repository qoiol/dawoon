package spring.project.controller;

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
    @GetMapping("/") //메인페이지로 이동
    public String index(){
        return "redirect:main";
    }

    @GetMapping("/main") //메인페이지
    public String mainPage(Model model){

        List<Exercise> ex = new ArrayList<>();

        //top3 인기 운동 조회

        List<User> tr = new ArrayList<>();

        //top3 인기 강사 조회

        model.addAttribute("ex", ex);
        model.addAttribute("tr", tr);

        model.addAttribute("userId", model.getAttribute("userId"));

        return "main";
    }
}
