package lk.ijse._13spring_boot.service;

import lk.ijse._13spring_boot.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void save(CustomerDTO customerDTO);
    List<CustomerDTO> getAll();
    void delete(int id);
    void update(CustomerDTO customerDTO);
}
