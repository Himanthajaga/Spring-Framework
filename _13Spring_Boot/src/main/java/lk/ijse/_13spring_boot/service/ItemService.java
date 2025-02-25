package lk.ijse._13spring_boot.service;

import lk.ijse._13spring_boot.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void save (ItemDTO itemDTO);
    List<ItemDTO> getAll();
    void delete(int id);
    void update(ItemDTO itemDTO);
}
