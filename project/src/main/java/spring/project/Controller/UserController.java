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

    @GetMapping("/user/login/form")
    public String loginForm(){
        return "/user/login";
    }
    @PostMapping("/user/login")
    public String login(LoginForm loginForm, Model model){
        //로그인 기능 구현
        return "redirect:/";
    }
    @GetMapping("/user/logout")
    public String logout(Model model){
        model.addAttribute("userId", null);
        return "redirect:/";
    }
    @GetMapping("/user/create/form")
    public String createForm(){
        return "/user/create";
    }
    @PostMapping("/user/create")
    public String create(CreateForm createForm){
        System.out.println("create");

        userService.validateDuplicateUser(createForm.getId());

        User user = new User();
        user.setId(createForm.getId());
        user.setEmail(createForm.getEmail());
        user.setUserType(createForm.getUserType());
        user.setPassword(createForm.getPassword());
        user.setName(createForm.getName());

        userService.join(user);

        return "redirect:/";
    }
}
