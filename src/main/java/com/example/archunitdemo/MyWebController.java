package com.example.archunitdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyWebController {

    @AuthUser
    @GetMapping
    public String getGood() {
        return null;
    }

    @AuthAdmin
    @RequestMapping
    public String postGood() {
        return null;
    }

    @GetMapping
    public String getBad() {
        return null;
    }

    @PostMapping
    public String postBad() {
        return null;
    }

}
