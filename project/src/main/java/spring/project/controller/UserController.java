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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.project.dto.response.LoginResponse;
import spring.project.dto.request.UserJoinRequest;
import spring.project.dto.request.LoginRequest;
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
        LoginResponse loginResponse;
        String redirectUri = (!referer.isEmpty() ? URLDecoder.decode(referer, StandardCharsets.UTF_8):"/");
        try{
            loginResponse = userService.login(loginRequest, response);
            JwtTokenUtils.addCookie("token", loginResponse.getToken(), response);
        } catch (Exception e){
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().append("<script>alert('").append(e.getMessage()).append("');location.href='/user/login?referer=").append(referer).append("';</script>");
            } catch (IOException ex) {
                log.error("login - error - ioexception");
            }
            return "/user/login";
        }

        session.setAttribute("userId", loginResponse.getUser().getId());
        session.setAttribute("userType", loginResponse.getUser().getUserType());
        session.setMaxInactiveInterval(3600);

        return "redirect:"+redirectUri;
    }

    @GetMapping("/user/logout") //로그아웃 -> 메인페이지로
    public String logout(HttpSession httpSession, HttpServletResponse response, @RequestParam(required = false) String action){
        httpSession.removeAttribute("userId");
        httpSession.removeAttribute("userType");
        JwtTokenUtils.removeCookie("token", response);

        if(action != null && action.equals("login")) {
            return "redirect:/user/login";
        }

        return "redirect:/";
    }

    @GetMapping("/user/create") //회원가입 화면으로 이동
    public String createForm(){
        return "/user/create";
    }

    @PostMapping("/user/create") //회원가입
    public String create(@Valid UserJoinRequest userJoinRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("id", userJoinRequest.getId());
        redirectAttributes.addFlashAttribute("password", userJoinRequest.getPassword());
        redirectAttributes.addFlashAttribute("email", userJoinRequest.getEmail());
        redirectAttributes.addFlashAttribute("name", userJoinRequest.getName());
        redirectAttributes.addFlashAttribute("userType", userJoinRequest.getUserType());

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/user/create";
        }

        try{
            userService.join(userJoinRequest);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/user/create";
        }

        return "redirect:/";
    }
}
