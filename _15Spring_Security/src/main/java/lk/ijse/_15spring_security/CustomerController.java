package lk.ijse._15spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private List<Customer> customers = new ArrayList<Customer>( List.of(
            new Customer("Kamal", 23),
            new Customer("Nimal", 24)
            ));
   @GetMapping
    public List<Customer> getAllCustomers(){
        return customers;
    }
    @PostMapping
    public String saveCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return "Customer Saved";
    }
    @GetMapping("/csrftoken")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
