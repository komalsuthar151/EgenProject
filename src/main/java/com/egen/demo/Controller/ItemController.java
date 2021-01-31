package com.egen.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class ItemController {
    @GetMapping("/hello")
    public String hello() {
         return "Welcome page";
    }
}
