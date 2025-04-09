package com.hms.patient;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testcontroller {
    @GetMapping("/sample")
    public String hello() {
        return "Hello World";
    }
}
