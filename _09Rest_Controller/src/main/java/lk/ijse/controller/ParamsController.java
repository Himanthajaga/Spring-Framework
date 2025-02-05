package lk.ijse.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("params")
@RestController
public class ParamsController {
    @GetMapping(params = {"id","name"})
    public String test1(@RequestParam (value = "id",required = false)String id,
                        @RequestParam (value = "name",required = false)String name){
        return "id : "+id + " name : "+name;
    }
}
