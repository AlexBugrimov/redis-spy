package dev.bug.spy.web.controller;

import dev.bug.spy.model.SecurityData;
import dev.bug.spy.service.SecurityDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/records")
public record RecordsController(SecurityDataService dataService) {

    @GetMapping
    public ModelAndView getAllRecords(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<SecurityData> securityDataPage = dataService.findAll(Pageable.ofSize(pageSize).withPage(page));
        return new ModelAndView("records");
    }

    @GetMapping("/{id}")
    public String getRecordById(Model model, @PathVariable("id") String id) {
        SecurityData securityData = dataService.findById(id);
        return "redirect:/records";
    }

    @PostMapping
    public String createRecord(Model model, @RequestParam(value = "count", defaultValue = "10") Long count) {
        long createdRecords = dataService.createRecordsFrom(count);
        return "redirect:/records";
    }

    @DeleteMapping
    public String deleteAllRecords(Model model) {
        long deletedRecords = dataService.deleteAllRecords();
        return "redirect:/records";
    }
}
