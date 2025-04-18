package spring.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.project.domain.Report;
import spring.project.service.ReportService;
import spring.project.service.ReviewService;
import spring.project.service.UserService;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final ReportService reportService;

    @GetMapping("/admin")
    public String adminPage(){
        return "/admin/adminPage";
    }

    @GetMapping("/admin/userlist")
    public String userlist(Model model){
        model.addAttribute("userlist" , userService.findUsers());
        return "/admin/userlist";
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
}
