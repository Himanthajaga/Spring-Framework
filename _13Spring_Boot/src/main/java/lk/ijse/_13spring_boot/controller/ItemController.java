package lk.ijse._13spring_boot.controller;

import lk.ijse._13spring_boot.dto.ItemDTO;
import lk.ijse._13spring_boot.service.impl.ItemServiceImpl;
import lk.ijse._13spring_boot.util.ResponseUtil;
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
    public ResponseUtil getCustomer(@RequestBody ItemDTO itemDTO){
        System.out.println(itemDTO);
        itemService.save(itemDTO);
        return new ResponseUtil(500,"Customer save failed",null);
    }
    @GetMapping(path = "/get")
    public ResponseUtil getAllItems(){
        return new ResponseUtil(201,"Item loaded successfully",itemService.getAll());
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseUtil deleteItem(@PathVariable int id){
        itemService.delete(id);
        return new ResponseUtil(201,"Item Deleted Successfully",null);
    }
    @PutMapping(path = "/update")
    public ResponseUtil updateItem(@RequestBody ItemDTO itemDTO){
        itemService.update(itemDTO);
       return new ResponseUtil(201,"Customer Updated successfully",null);
    }
}
