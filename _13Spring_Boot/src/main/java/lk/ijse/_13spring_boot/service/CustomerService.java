package lk.ijse._13spring_boot.service;

import lk.ijse._13spring_boot.dto.CustomerDTO;
import lk.ijse._13spring_boot.entity.Customer;
import lk.ijse._13spring_boot.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    public boolean save (CustomerDTO customerDTO){
      Customer customer =   new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        customerRepo.save(customer);
        return true;
    }

    public List<CustomerDTO> getAll() {
       return customerRepo.findAll().stream()
               .map(customer -> new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()))
        .collect(Collectors.toList());
    }
    public boolean delete(int id){
        customerRepo.deleteById(id);
        return true;
    }

    public boolean update(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        customerRepo.save(customer);
        return true;
    }
}
