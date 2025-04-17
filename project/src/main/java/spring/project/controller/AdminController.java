package spring.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.project.service.UserService;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final UserService userService;

    @GetMapping("/admin")
    public String adminPage(){
        return "/admin/adminPage";
    }

    @GetMapping("/admin/userlist")
    public String userlist(Model model){
        model.addAttribute("userlist" , userService.findUsers());
        return "/admin/userlist";
    }
}
