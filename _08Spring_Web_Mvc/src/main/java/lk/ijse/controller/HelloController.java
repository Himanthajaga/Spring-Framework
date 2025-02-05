package lk.ijse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//views return krnne @controller eken
@Controller
@RequestMapping("/hello")
public class HelloController {

    public HelloController() {
        System.out.println("Hello Controller...");
    }
    @GetMapping
    public String hello(){
        return "index";
    }
}
