package spring.project.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.project.domain.User;
import spring.project.dto.UserJoinRequest;
import spring.project.dto.LoginRequest;
import spring.project.service.UserService;
import spring.project.util.JwtTokenUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login") //로그인 화면으로 이동
    public String loginForm(Model model, @RequestParam(required = false, defaultValue = "") String referer){
        if(!referer.isEmpty())
            model.addAttribute("referer", referer);
        return "/user/login";
    }

//    @PostMapping("/user/login") //로그인
//    public String login(HttpSession session, LoginForm loginForm, Model model){
//        //로그인 기능 구현
//        User loginUser = new User();
//        loginUser.setId(loginForm.getId());
//        loginUser.setPassword(loginForm.getPassword());
//
//        try{
//            loginUser = userService.login(loginUser);
//        }catch (Exception e){
//            model.addAttribute("exception", true);
//            model.addAttribute("message", e.getMessage());
//            return "/user/login";
//        }
//
//        session.setAttribute("userId", loginUser.getId());
//        session.setAttribute("userType", loginUser.getUserType());
//        session.setMaxInactiveInterval(3600);
//
//        return "redirect:/";
//    }

    @PostMapping("/user/login") //로그인
    public String login(HttpSession session, LoginRequest loginRequest, Model model, HttpServletResponse response,
                        @RequestParam(required = false, defaultValue = "") String referer){


        log.info("user login referer: {}", referer);
        String token;
        try{
            token = userService.login2(loginRequest);
            JwtTokenUtils.addCookie("token", token, response);
        } catch (Exception e){
            model.addAttribute("exception", true);
            model.addAttribute("message", e.getMessage());
            return "/user/login?referer="+referer;
        }

        session.setAttribute("userId", loginRequest.getId());
        session.setAttribute("userType", loginRequest.getUserType());
        session.setMaxInactiveInterval(3600);

        if(!referer.isEmpty()) {
            return "redirect:"+ URLDecoder.decode(referer, StandardCharsets.UTF_8);
        }

        return "redirect:/";
    }

    @GetMapping("/user/logout") //로그아웃 -> 메인페이지로
    public String logout(HttpSession httpSession, HttpServletResponse response){
        httpSession.removeAttribute("userId");
        httpSession.removeAttribute("userType");
        JwtTokenUtils.removeCookie("token", response);
        return "redirect:/";
    }

    @GetMapping("/user/create") //회원가입 화면으로 이동
    public String createForm(){
        return "/user/create";
    }

    @PostMapping("/user/create") //회원가입
    public String create(@Valid UserJoinRequest userJoinRequest, BindingResult bindingResult, Model model){

        try{
//            userService.validateDuplicateUser(createForm.getId());
            userService.join2(userJoinRequest);
        }catch (Exception e){
            model.addAttribute("exception", true);
            model.addAttribute("message", e.getMessage());
            return "/user/create";
        }

//        User user = new User();
//        user.setId(createForm.getId());
//        user.setEmail(createForm.getEmail());
//        user.setUserType(createForm.getUserType());
//        user.setPassword(createForm.getPassword());
//        user.setName(createForm.getName());

//        userService.join(user);

        return "redirect:/";
    }




}
