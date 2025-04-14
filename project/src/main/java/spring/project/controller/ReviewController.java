package spring.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.project.domain.*;
import spring.project.service.ReportService;
import spring.project.service.ReviewService;
import spring.project.service.WorkoutService;

import java.util.List;

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
        String keyword = (session.getAttribute("keyword")==null)?null:session.getAttribute("keyword").toString();
        Long workoutId = (session.getAttribute("sworkoutid")==null)?null:Long.parseLong(session.getAttribute("sworkoutid").toString());
        String orderby = (session.getAttribute("orderby")==null)?"r.postedDate":session.getAttribute("orderby").toString();

        session.setAttribute("reviewList", reviewService.findReviews(keyword, workoutId, orderby));
        session.setAttribute("wList", workoutService.getAllWorkoutList());

        session.setAttribute("orderby", orderby);
        session.setAttribute("sworkoutid", workoutId);
        session.setAttribute("keyword", keyword);
        return "/review/reviewPage";
    }

    @GetMapping("/review/search")
    public String reviewSearch(Model model, HttpSession session, ReviewSearchForm reviewSearchForm){
        String keyword = reviewSearchForm.getKeyword();
        Long workoutId = reviewSearchForm.getSworkoutid();
        String orderby = (reviewSearchForm.getOrderby()==null)?"r.postedDate":reviewSearchForm.getOrderby();

        session.setAttribute("reviewList", reviewService.findReviews(keyword, workoutId, orderby));
        session.setAttribute("wList", workoutService.getAllWorkoutList());

        session.setAttribute("orderby", orderby);
        session.setAttribute("sworkoutid", workoutId);
        session.setAttribute("keyword", keyword);
        return "/review/reviewPage";
    }


    @PostMapping("/review/create")
    public String createReview(ReviewForm reviewForm, HttpSession session) {

        Review review = new Review();

        review.setScore(reviewForm.getScore());
        review.setTitle(reviewForm.getTitle());
        review.setContent(reviewForm.getContent());
        review.setWorkout(Workout.builder().workoutId(reviewForm.getWorkoutId()).build());

        review.setUser(User.builder().id(session.getAttribute("userId").toString()).build());

        reviewService.createReview(review);

        session.setAttribute("orderType", null);
        session.setAttribute("workoutType", null);
        session.setAttribute("orderby", null);

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

    @GetMapping("/admin/report")
    public String reportPage(Model model){
        model.addAttribute("reportlist", reportService.findReports());
        return "/admin/reportlist";
    }

    @GetMapping("/admin/report/{id}")
    public String reportView(@PathVariable("id") long id, Model model){
        Report report = reportService.findReport(id);
        model.addAttribute("report", report);
        model.addAttribute("review", reviewService.findOne(report.getReviewId()));
        return "/admin/reportview";
    }

    @GetMapping("/admin/report/{id}/delete")
    public String reportDelete(@PathVariable("id") long id){
        long reviewId = reportService.findReport(id).getReviewId();
        reviewService.delete(reviewId);
        reportService.deleteReport(reviewId);
        return "redirect:/admin/report";
    }

    @GetMapping("/admin/report/{id}/return")
    public String reportReturn(@PathVariable("id") long id){
        reportService.returnReport(id);
        return "redirect:/admin/report";
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
