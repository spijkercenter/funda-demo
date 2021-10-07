package nl.avans.informatica.funda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public ModelAndView redirectToSwagger() {
        return new ModelAndView("redirect:/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config");
    }
}
