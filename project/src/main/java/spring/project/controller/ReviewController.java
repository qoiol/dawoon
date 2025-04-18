package spring.project.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.project.domain.*;
import spring.project.dto.ReportForm;
import spring.project.dto.ReviewForm;
import spring.project.dto.ReviewSearchForm;
import spring.project.service.ReportService;
import spring.project.service.ReviewService;
import spring.project.service.WorkoutService;

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
    public String reviewPage(Model model, @Valid ReviewSearchForm reviewSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/review";
        }

        model.addAttribute("reviewList", reviewService.findReviews(reviewSearchForm));
        model.addAttribute("wList", workoutService.getAllWorkoutList());

        return "/review/reviewPage";
    }

    @PostMapping("/review/regist")
    public String createReview(@Valid ReviewForm reviewForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            reviewService.createReview(reviewForm, session.getAttribute("userId").toString());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "리뷰 등록 실패. 다시 시도해주세요.");
        }
        return "redirect:/review";
    }

    @GetMapping("/review/{id}/delete")
    public String deleteReview(Model model, HttpSession session, @PathVariable("id") long id) {

        reviewService.delete(id);

        model.addAttribute("message", "삭제되었습니다.");
        session.setAttribute("orderType", null);
        session.setAttribute("workoutType", null);
        session.setAttribute("orderby", null);
        return "redirect:/review";
    }

    @PostMapping("/review/report")
    public String report(ReportForm reportForm, HttpSession session) {
        //신고 처리
        Report report = new Report();
        report.setReviewId(reportForm.getReviewId());
        report.setUserId(session.getAttribute("userId").toString());
        report.setReportReason(reportForm.getReportReason());
        reportService.create(report);
        return "redirect:/review";
    }



    @GetMapping("/review/{id}/like")
    public String like(@PathVariable("id") long id, HttpSession session, Model model){
        Likey likey = new Likey();
//        likey.setReviewId(id);
//        likey.setUserId(session.getAttribute("userId").toString());
//        LikeyId likeyId = new LikeyId(id, );
        String exception = reviewService.clickLikey(Likey.builder()
                .review(Review.builder()
                        .id(id).build())
                .user(User.builder()
                        .id(session.getAttribute("userId").toString()).build()).build()
        );
        if(exception != null){
            model.addAttribute("exception", exception);
            return "/review/reviewPage";
        }
        return "redirect:/review";
    }
}
