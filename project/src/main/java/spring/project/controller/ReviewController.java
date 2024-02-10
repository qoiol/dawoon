package spring.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.project.domain.Report;
import spring.project.domain.Review;
import spring.project.domain.Workout;
import spring.project.repository.WorkoutRepository;
import spring.project.service.ReportService;
import spring.project.service.ReviewService;
import spring.project.service.WorkoutService;

import java.util.ArrayList;

@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final ReportService reportService;
    private final WorkoutService workoutService;

    @Autowired
    public ReviewController(ReviewService reviewService, ReportService reportService, WorkoutService workoutService) {
        this.reviewService = reviewService;
        this.reportService = reportService;
        this.workoutService = workoutService;
    }

    @GetMapping("/review")
    public String reviewPage(Model model, HttpSession session){
        model.addAttribute("reviewList", reviewService.findReviews());
        model.addAttribute("wList", workoutService.getAllWorkoutList());
        session.setAttribute("orderType", session.getAttribute("orderType"));
        session.setAttribute("workoutType", session.getAttribute("workoutType"));
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

        session.setAttribute("orderType", null);
        session.setAttribute("workoutType", null);
        return "redirect:/review";
    }

    @GetMapping("/review/{id}/delete")
    public String deleteReview(Model model, HttpSession session){
        try{
            reviewService.delete((long)model.getAttribute("id"));
        }
        catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "/review/reviewPage";
        }

        model.addAttribute("message", "삭제되었습니다.");
        session.setAttribute("orderType", null);
        session.setAttribute("workoutType", null);
        return "redirect:/review";
    }

    @GetMapping("/review/{id}/like")
    public String recommendReview(long id, HttpSession session){
        //추천 기능
        return "redirect:/review";
    }

    @PostMapping("/review/report")
    public String report(ReportForm reportForm, HttpSession session){
        //신고 처리
        Report report = new Report();
        report.setReviewId(reportForm.getReviewId());
        report.setUserId(session.getAttribute("userId").toString());
        report.setReportReason(reportForm.getReportReason());
        reportService.create(report);
        return "redirect:/review";
    }

}
