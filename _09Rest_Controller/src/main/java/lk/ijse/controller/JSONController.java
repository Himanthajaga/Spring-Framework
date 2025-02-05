package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("json")
public class JSONController {
    @PostMapping
    public String test1(@RequestBody CustomerDTO customerDTO) {
       return customerDTO.getFirstName();
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public CustomerDTO test2() {
        return new CustomerDTO
                ("maleesha", "Kamal", 23);
    }
    @GetMapping(path = "data",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<CustomerDTO>test3(){
        ArrayList<CustomerDTO>customerDTOS = new ArrayList<>();
        customerDTOS.add(new CustomerDTO("maleesha","kamal",23));
        customerDTOS.add(new CustomerDTO("maleesha","kamal",24));
        customerDTOS.add(new CustomerDTO("maleesha","kamal",25));
        return customerDTOS;
    }

}
