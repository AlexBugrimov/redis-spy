package dev.bug.spy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SpyController {

    private final SpyService spyService;

    @GetMapping
    public String index(Model model) {
        Page<Object> page = spyService.getAllByPage(1, 10);
        model.addAttribute("objects", page.getContent());
        return "index";
    }
}
