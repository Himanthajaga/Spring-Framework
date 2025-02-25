package lk.ijse._13spring_boot.service.impl;

import lk.ijse._13spring_boot.dto.CustomerDTO;
import lk.ijse._13spring_boot.entity.Customer;
import lk.ijse._13spring_boot.repository.CustomerRepo;
import lk.ijse._13spring_boot.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;
    public void save (CustomerDTO customerDTO){
//      Customer customer =   new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        if (!customerRepo.existsById(customerDTO.getId())){
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            customerRepo.save(customer);
        }
        throw new RuntimeException("Customer already exists");
    }

    public List<CustomerDTO> getAll() {
        return modelMapper.map(
                customerRepo.findAll(),
                new TypeToken<List<CustomerDTO>>(){}.getType());
    }
    public void delete(int id){
        if (customerRepo.existsById(id)){
            customerRepo.deleteById(id);
        }
        throw new RuntimeException("Customer not found");
    }

    public void update(CustomerDTO customerDTO) {
//        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
       if (
                customerRepo.existsById(customerDTO.getId())
         ){
              Customer customer = modelMapper.map(customerDTO, Customer.class);
              customerRepo.save(customer);
              return;
         }
        throw new RuntimeException("Customer not found");
    }
}
