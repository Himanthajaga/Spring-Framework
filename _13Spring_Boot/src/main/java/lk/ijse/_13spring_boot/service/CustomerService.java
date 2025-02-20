package lk.ijse._13spring_boot.service;

import lk.ijse._13spring_boot.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    boolean save(CustomerDTO customerDTO);
    List<CustomerDTO> getAll();
    boolean delete(int id);
    boolean update(CustomerDTO customerDTO);
}
