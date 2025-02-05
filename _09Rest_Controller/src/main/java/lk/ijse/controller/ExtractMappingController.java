package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exact")
public class ExtractMappingController {
    @GetMapping("test1")
    public String exactmapping(){
        return "get mapping invoked!";
    }
    @GetMapping("test2")
    public String exactmapping2(){
        return "get mapping2 invoked!";
    }
    @GetMapping("test2/example/1234")
    public String exactmapping3(){
        return "get mapping2 invoked! test2/example/1234";
    }
}
