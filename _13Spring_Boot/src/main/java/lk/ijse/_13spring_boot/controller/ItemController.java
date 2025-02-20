package lk.ijse._13spring_boot.controller;

import lk.ijse._13spring_boot.dto.ItemDTO;
import lk.ijse._13spring_boot.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin(origins = "http://localhost:63342")
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;
    @PostMapping(path = "/save")
    public boolean getCustomer(@RequestBody ItemDTO itemDTO){
        System.out.println(itemDTO);
        boolean res = itemService.save(itemDTO);
        return res;
    }
    @GetMapping(path = "/get")
    public List<ItemDTO> getAllItems(){
        return itemService.getAll();
    }
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteItem(@PathVariable int id){
        return itemService.delete(id);
    }
    @PutMapping(path = "/update")
    public boolean updateItem(@RequestBody ItemDTO itemDTO){
        return itemService.update(itemDTO);
    }
}
