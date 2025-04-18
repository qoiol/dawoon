package spring.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.project.domain.Reservation;
import spring.project.domain.User;
import spring.project.service.ReservationService;
import spring.project.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final UserService userService;
    private final ReservationService reservationService;

    @GetMapping("/mypage")
    public String getReservation(HttpServletRequest request, Model model) {
        System.out.println("mypage controller IN");
        String userId = (String)request.getSession().getAttribute("userId");
        User user = userService.findOne(userId).get();

        System.out.println("userId : " + userId);
        //userId로 예약목록 받아와서
        List<Reservation> reservationList = reservationService.findByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("reservationList",reservationList);

        return "/user/myPage";
    }
}