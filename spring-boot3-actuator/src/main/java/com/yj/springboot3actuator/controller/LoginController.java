package com.yj.springboot3actuator.controller;

import com.yj.springboot3actuator.observation.IndexObservation;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Slf4j
@Controller
public class LoginController {

    private final IndexObservation indexObservation;



    @GetMapping("/logout")
    public void logout() {
        log.info("log out...");
    }



    @GetMapping("/")
    @ResponseBody
    @Timed(value = "demo.greeting", description = "Time taken to return greeting")
    public String index() {
        log.info("this is index page.");

        indexObservation.observe();
        return "index page.";
    }

}
