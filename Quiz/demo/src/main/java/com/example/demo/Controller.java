package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController("/api")
public class Controller {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello0";
    }
}
