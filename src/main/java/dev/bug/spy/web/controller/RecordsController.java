package dev.bug.spy.web.controller;

import dev.bug.spy.model.SecurityData;
import dev.bug.spy.service.SecurityDataService;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
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
    public ModelAndView getAllRecords(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        PagedListHolder<SecurityData> securityDataPage = dataService.findAll(PageRequest.of(page, pageSize));
        return new ModelAndView("records")
                .addObject("records", securityDataPage.getPageList())
                .addObject("counts", COUNT_CREATED_RECORDS)
                .addObject("totalPages", securityDataPage.getPageCount())
                .addObject("totalElements", securityDataPage.getNrOfElements())
                .addObject("currentPage", page)
                .addObject("pageSize", pageSize);
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
