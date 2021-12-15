package dev.bug.spy.web.controller;

import dev.bug.spy.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public record HomeController(CommonService commonService) {

    @GetMapping
    public String checkStatus(Model model) {
        boolean isUp = commonService.isUp();
        model.addAttribute("isUp", isUp);
        return "index";
    }
}
