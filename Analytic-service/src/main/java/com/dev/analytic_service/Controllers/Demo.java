package com.dev.analytic_service.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class Demo {

    @GetMapping("/demo")
    private String demo(){

        return "Inside Demo Controller";

    }
}
