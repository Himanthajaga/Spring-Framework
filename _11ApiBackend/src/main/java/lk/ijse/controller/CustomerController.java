package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private List<CustomerDTO> customers = new ArrayList<>();
    @PostMapping(path = "save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customers.add(customerDTO);
        return customerDTO;
    }
    @PutMapping("update")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        for (int i = 0; i < customers.size(); i++) {
            CustomerDTO excitingCustomer = customers.get(i);
           if (excitingCustomer.getId().equals(customerDTO.getId())){
               excitingCustomer.setName(customerDTO.getName());
               excitingCustomer.setAddress(customerDTO.getAddress());
               excitingCustomer.setAge(customerDTO.getAge());
               return excitingCustomer;
           }

        }
        return customerDTO;
    }

    @GetMapping(path = "getAll")
    public List<CustomerDTO> getAllCustomers(){
        return customers;
    }
    @DeleteMapping(path = "delete/{id}")
    public boolean deleteCustomer(@PathVariable (value = "id")String id){
        for (int i = 0; i < customers.size(); i++) {
            CustomerDTO excitingCustomer = customers.get(i);
            if (excitingCustomer.getId().equals(id)){
                customers.remove(i);
                return true;
            }
        }
        return false;
    }
}
