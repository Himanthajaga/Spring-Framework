package lk.ijse._13spring_boot.controller;

import lk.ijse._13spring_boot.dto.CustomerDTO;
import lk.ijse._13spring_boot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping(path = "/save")
    public boolean getCustomer(@RequestBody CustomerDTO customerDTO){
        System.out.println(customerDTO);
        boolean res = customerService.save(customerDTO);
        return res;
    }
    @GetMapping(path = "/get")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAll();
    }
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteCustomer(@PathVariable int id){
        return customerService.delete(id);
    }
    @PutMapping(path = "/update")
    public boolean updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.update(customerDTO);
    }
}
