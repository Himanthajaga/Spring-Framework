package lk.ijse._13spring_boot.service.impl;

import lk.ijse._13spring_boot.dto.ItemDTO;
import lk.ijse._13spring_boot.entity.Item;
import lk.ijse._13spring_boot.repository.ItemRepo;
import lk.ijse._13spring_boot.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;
    public boolean save (ItemDTO itemDTO){
//        Item item =   new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
        itemRepo.save(modelMapper.map(itemDTO, Item.class));
        return true;
    }

    public List<ItemDTO> getAll() {
        return modelMapper.map(
                itemRepo.findAll(),
                new TypeToken<List<ItemDTO>>(){}.getType());
    }
    public boolean delete(int id){
       itemRepo.deleteById(id);
        return true;
    }

    public boolean update(ItemDTO itemDTO) {
//        Item item =   new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
       itemRepo.save(modelMapper.map(itemDTO, Item.class));
        return true;
    }
}
