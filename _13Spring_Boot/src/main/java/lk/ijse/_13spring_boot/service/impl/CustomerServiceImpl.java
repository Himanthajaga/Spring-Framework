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
    public boolean save (CustomerDTO customerDTO){
//      Customer customer =   new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepo.save(customer);
        return true;
    }

    public List<CustomerDTO> getAll() {
        return modelMapper.map(
                customerRepo.findAll(),
                new TypeToken<List<CustomerDTO>>(){}.getType());
    }
    public boolean delete(int id){
        customerRepo.deleteById(id);
        return true;
    }

    public boolean update(CustomerDTO customerDTO) {
//        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        return true;
    }
}
