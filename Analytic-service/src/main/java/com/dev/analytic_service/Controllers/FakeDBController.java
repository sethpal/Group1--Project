package com.dev.analytic_service.Controllers;


import com.dev.analytic_service.Services.FakeDBService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor
@RestController
public class FakeDBController {

    FakeDBService fakeDBService;

    @PostMapping("/setDB")
    public String set_DB(){
        fakeDBService.set_DB();
        return "DB got set now";
    }


}
