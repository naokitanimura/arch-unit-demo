package com.example.archunitdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface MyApi {
    @GetMapping
    ResponseEntity<String> getGood();

    @PostMapping
    ResponseEntity<Void> postGood();

    @RequestMapping
    ResponseEntity<String> getBad();

    @PostMapping
    ResponseEntity<Void> postBad();

}
