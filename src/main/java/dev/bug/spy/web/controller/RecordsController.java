package dev.bug.spy.web.controller;

import dev.bug.spy.model.SecurityData;
import dev.bug.spy.service.SecurityDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/records")
public record RecordsController(SecurityDataService dataService) {

    private static final List<Integer> COUNT_CREATED_RECORDS = List.of(10, 100, 500, 1000, 5000);

    @GetMapping
    public ModelAndView getAllRecords(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<SecurityData> securityDataPage = dataService.findAll(Pageable.ofSize(pageSize).withPage(page));
        return new ModelAndView("records")
                .addObject("records", securityDataPage.getContent())
                .addObject("counts", COUNT_CREATED_RECORDS);
    }

    @GetMapping("/create")
    public ModelAndView createRecord(@RequestParam(value = "count", defaultValue = "10") Long count) {
        dataService.createRecordsFrom(count);
        return new ModelAndView("redirect:/records");
    }

    @GetMapping("/clear")
    public ModelAndView deleteAllRecords() {
        dataService.deleteAllRecords();
        return new ModelAndView("redirect:/records");
    }
}
