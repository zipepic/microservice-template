package com.example.jagertrace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceController {
    @GetMapping
    public String get(){
        return "OK";
    }
}
