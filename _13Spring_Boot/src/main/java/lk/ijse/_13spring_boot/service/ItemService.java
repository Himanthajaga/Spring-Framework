package lk.ijse._13spring_boot.service;

import lk.ijse._13spring_boot.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    boolean save (ItemDTO itemDTO);
    List<ItemDTO> getAll();
    boolean delete(int id);
    boolean update(ItemDTO itemDTO);
}
