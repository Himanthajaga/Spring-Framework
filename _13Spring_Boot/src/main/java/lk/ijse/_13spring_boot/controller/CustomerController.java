package lk.ijse._13spring_boot.controller;

import lk.ijse._13spring_boot.dto.CustomerDTO;
import lk.ijse._13spring_boot.service.impl.CustomerServiceImpl;
import lk.ijse._13spring_boot.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin(origins = "http://localhost:63342")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;
    @PostMapping(path = "/save")
    public ResponseUtil getCustomer(@RequestBody CustomerDTO customerDTO) {
        System.out.println("customer save");
 customerService.save(customerDTO);
           return new ResponseUtil(201,"customer saved successfully",null);
    }
    @GetMapping(path = "/get")
    public ResponseUtil getAllCustomers(){
       return new ResponseUtil(201,"customer saved successfully",customerService.getAll());
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseUtil deleteCustomer(@PathVariable int id){
        customerService.delete(id);
        return new ResponseUtil(201,"Customer Deleted Successfully",null);
    }
    @PutMapping(path = "/update")
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.update(customerDTO);
       return new ResponseUtil(201,"Customer Updated Successfully",null);
    }
}
