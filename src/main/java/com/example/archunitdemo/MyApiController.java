package com.example.archunitdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyApiController implements MyApi{

    @AuthUser
    @Override
    public ResponseEntity<String> getGood() {
        return null;
    }

    @AuthAdmin
    @Override
    public ResponseEntity<Void> postGood() {
        return null;
    }

    @Override
    public ResponseEntity<String> getBad() {
        return null;
    }

    @Override
    public ResponseEntity<Void> postBad() {
        return null;
    }
}
