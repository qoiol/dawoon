package spring.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.project.domain.User;
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
    public String login(LoginForm loginForm, Model model){
        //로그인 기능 구현
        return "redirect:/";
    }
    @GetMapping("/user/logout") //로그아웃 -> 메인페이지로
    public String logout(Model model){
        model.addAttribute("userId", null);
        return "redirect:/";
    }
    @GetMapping("/user/create/form") //회원가입 화면으로 이동
    public String createForm(){
        return "/user/create";
    }
    @PostMapping("/user/create") //회원가입
    public String create(CreateForm createForm, Model model){
        System.out.println("create");

        try{
            userService.validateDuplicateUser(createForm.getId());
        }catch (Exception e){
            model.addAttribute("registerFailed", true);
            model.addAttribute("exception", e.getMessage());
            return "/user/create";
        }


        User user = new User();
        user.setId(createForm.getId());
        user.setEmail(createForm.getEmail());
        user.setUserType(createForm.getUserType());
        user.setPassword(createForm.getPassword());
        user.setName(createForm.getName());

        System.out.println("user.getId() = " + user.getId());

        userService.join(user);

        return "redirect:/";
    }
}
