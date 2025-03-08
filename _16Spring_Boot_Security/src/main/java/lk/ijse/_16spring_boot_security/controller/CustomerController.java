package lk.ijse._16spring_boot_security.controller;

import jakarta.validation.Valid;
import lk.ijse._16spring_boot_security.dto.CustomerDTO;
import lk.ijse._16spring_boot_security.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> createCustomer(@Valid @RequestBody  CustomerDTO customerDTO) {
        ResponseDTO responseDTO = new ResponseDTO(
                "User created successfully",
                HttpStatus.CREATED.value(),
                customerDTO);
        return new ResponseEntity
                (responseDTO, HttpStatus.CREATED);
    }
}
