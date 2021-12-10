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

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class SecurityDataController {

    private static final String PAGE_DEFAULT = "0";
    private static final String PAGE_SIZE_DEFAULT = "20";
    private final RedisSpyService spyService;

    @GetMapping("/items")
    public String getAllByPage(Model model,
                               @RequestParam(value = "page", defaultValue = PAGE_DEFAULT) int page,
                               @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE_DEFAULT) int pageSize) {
        Page<SecurityData> securityPage = spyService.findAllBy(page, pageSize);
        model.addAttribute("securities", securityPage.getContent());
        int totalPages = securityPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.range(0, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("totalPages", securityPage.getTotalPages());
            model.addAttribute("number", securityPage.getNumber());
            model.addAttribute("size", pageSize);
        }
        return "index";
    }

    @PostMapping
    public String actionDatabase(@RequestParam("action") String action) {
        if (action.equals("clear")) {
            spyService.clearDatabase();
        }
        if (action.equals("initialize")) {
            spyService.createDataFrom(20);
            return String.format("redirect:/items?page=%s&pageSize=%s", PAGE_DEFAULT, PAGE_SIZE_DEFAULT);
        }
        return "redirect:/items";
    }
}
