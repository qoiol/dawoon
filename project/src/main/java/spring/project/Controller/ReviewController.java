package spring.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.project.domain.Review;

import java.util.ArrayList;

@Controller
public class ReviewController {
    @GetMapping("/review")
    public String reviewPage(Model model){
        model.addAttribute("reviewList", new ArrayList<Review>());
        return "/review/reviewPage";
    }
}
