package dev.bug.spy.web;

import dev.bug.spy.model.SecurityData;
import dev.bug.spy.service.RedisSpyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SecurityDataController {

    private final RedisSpyService spyService;

    @GetMapping
    public String getAllByPage(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "pageSize", defaultValue = "100") int pageSize) {
        Page<SecurityData> data = spyService.findAllBy(page, pageSize);
        model.addAttribute("securities", data.getContent());
        return "index";
    }

    @PostMapping
    public String actionDatabase(@RequestParam("action") String action) {
        if (action.equals("clear")) {
            spyService.clearDatabase();
        }
        if (action.equals("initialize")) {
            spyService.initializeDatabase();
        }
        return "redirect:/";
    }
}
