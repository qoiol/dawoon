package spring.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.project.domain.User;
import spring.project.dto.CreateForm;
import spring.project.dto.LoginForm;
import spring.project.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login/form") //로그인 화면으로 이동
    public String loginForm(){
        return "/user/login";
    }

    @PostMapping("/user/login") //로그인
    public String login(HttpSession session, LoginForm loginForm, Model model){
        //로그인 기능 구현
        User loginUser = new User();
        loginUser.setId(loginForm.getId());
        loginUser.setPassword(loginForm.getPassword());

        try{
            loginUser = userService.login(loginUser);
        }catch (Exception e){
            model.addAttribute("exception", true);
            model.addAttribute("message", e.getMessage());
            return "/user/login";
        }

        session.setAttribute("userId", loginUser.getId());
        session.setAttribute("userType", loginUser.getUserType());
        session.setMaxInactiveInterval(3600);

        return "redirect:/";
    }

    @GetMapping("/user/logout") //로그아웃 -> 메인페이지로
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/create/form") //회원가입 화면으로 이동
    public String createForm(){
        return "/user/create";
    }

    @PostMapping("/user/create") //회원가입
    public String create(CreateForm createForm, Model model){

        try{
            userService.validateDuplicateUser(createForm.getId());
        }catch (Exception e){
            model.addAttribute("exception", true);
            model.addAttribute("message", e.getMessage());
            return "/user/create";
        }

        User user = new User();
        user.setId(createForm.getId());
        user.setEmail(createForm.getEmail());
        user.setUserType(createForm.getUserType());
        user.setPassword(createForm.getPassword());
        user.setName(createForm.getName());

        userService.join(user);

        return "redirect:/";
    }

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
