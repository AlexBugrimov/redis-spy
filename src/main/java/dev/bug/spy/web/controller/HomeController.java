package dev.bug.spy.web.controller;

import dev.bug.spy.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public record HomeController(CommonService commonService) {

    @GetMapping
    public ModelAndView checkStatus() {
        boolean isUp = commonService.isUp();
        return new ModelAndView("index").addObject("isUp", isUp);
    }
}
