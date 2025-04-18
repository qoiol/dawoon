package spring.project.controller;

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
import spring.project.dto.UserJoinRequest;
import spring.project.dto.LoginRequest;
import spring.project.service.UserService;
import spring.project.util.JwtTokenUtils;

import java.io.IOException;
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

    @PostMapping("/user/login") //로그인
    public String login(HttpSession session, LoginRequest loginRequest, HttpServletResponse response,
                        @RequestParam(required = false, defaultValue = "") String referer) {
        String token;
        String redirectUri = (!referer.isEmpty() ? URLDecoder.decode(referer, StandardCharsets.UTF_8):"/");
        try{
            token = userService.login(loginRequest);
            JwtTokenUtils.addCookie("token", token, response);
        } catch (Exception e){
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().append("<script>alert('").append(e.getMessage()).append("');location.href='/user/login?referer=").append(referer).append("';</script>");
            } catch (IOException ex) {
                log.error("login - error - ioexception");
            }
            return "/user/login";
        }

        session.setAttribute("userId", loginRequest.getId());
        session.setAttribute("userType", loginRequest.getUserType());
        session.setMaxInactiveInterval(3600);

        return "redirect:"+redirectUri;
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
            userService.join(userJoinRequest);
        } catch (Exception e){
            model.addAttribute("exception", true);
            model.addAttribute("message", e.getMessage());
            return "/user/create";
        }

        return "redirect:/";
    }
}
