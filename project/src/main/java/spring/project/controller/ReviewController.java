package spring.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.project.domain.Exercise;
import spring.project.domain.Review;
import spring.project.service.ReviewService;

import java.util.ArrayList;

@Controller
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public String reviewPage(Model model, HttpSession session){
        model.addAttribute("reviewList", reviewService.findReviews());
        model.addAttribute("wList", new ArrayList<Exercise>());
        session.setAttribute("orderType", null);
        session.setAttribute("workoutType", null);
        return "/review/reviewPage";
    }

    @PostMapping("/review/create")
    public String createReview(ReviewForm reviewForm, HttpSession session){

        Review review = new Review();
        review.setScore(reviewForm.getScore());
        review.setTitle(reviewForm.getTitle());
        review.setContent(reviewForm.getContent());
        review.setWorkoutId(reviewForm.getWorkoutId());

        review.setUserId(session.getAttribute("userId").toString());

        return "redirect:/review";
    }

    @PostMapping("/review/delete")
    public String deleteReview(Model model){
        try{
            reviewService.delete((long)model.getAttribute("id"));
        }
        catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "/review/reviewPage";
        }

        model.addAttribute("message", "삭제되었습니다.");
        return "redirect:/review";
    }

}
