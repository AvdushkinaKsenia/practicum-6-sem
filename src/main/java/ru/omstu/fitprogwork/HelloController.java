package ru.omstu.fitprogwork;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}