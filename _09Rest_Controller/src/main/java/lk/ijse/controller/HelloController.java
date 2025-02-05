package lk.ijse.controller;

import org.springframework.web.bind.annotation.*;

//views return krnne @controller eken
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(){
        return "hello world";
    }
    @PostMapping
    public String post(){
        return "post";
    }
    @PutMapping
    public String put(){
        return "put";
    }
    @DeleteMapping
    public String delete(){
        return "delete mapping";
    }
    @PatchMapping
    public String patch(){
        return "patch mapping";
    }
}
