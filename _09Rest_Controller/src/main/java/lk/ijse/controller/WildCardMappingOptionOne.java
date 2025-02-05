package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("one")
public class WildCardMappingOptionOne {

//    one/test/1/hello
//    one/test//hello
//    one/test/ass/hello/assds
    @GetMapping("test/*hello")
    public String test(){
        return "get mapping option one";
    }
    @GetMapping("test/*/*")
    public String test1(){
        return "get mapping option 2 one";
    }
}
