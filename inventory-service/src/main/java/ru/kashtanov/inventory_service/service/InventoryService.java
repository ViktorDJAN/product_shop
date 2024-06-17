package ru.kashtanov.inventory_service.service;

import org.springframework.stereotype.Service;
import ru.kashtanov.inventory_service.dto.InventoryRequestDto;
import ru.kashtanov.inventory_service.dto.InventoryResponse;
import ru.kashtanov.inventory_service.model.Inventory;
import ru.kashtanov.inventory_service.repository.InventoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryResponse> isInStock(String skuCode){
        List<Inventory>inventories = inventoryRepository.findBySkuCode(skuCode);
        List<InventoryResponse> collect = inventories.stream()
                .map(inventory -> new InventoryResponse(inventory.getSkuCode(),
                        inventory.getQuantity() > 0)).collect(Collectors.toList());
        return collect;
    }

    public List<InventoryResponse> listIsInStockInto(List<String> skuCode){
        System.out.println(skuCode);

        // search one by one element in order to find a suitable one ,add an item to the common list
        List<Inventory> commonInventoryList = new ArrayList<>();
        List<List<Inventory>> totalList = new ArrayList<>();
        for(String itemSearch:skuCode){
            if(inventoryRepository.findBySkuCode(itemSearch)!=null){
                totalList.add(inventoryRepository.findBySkuCode(itemSearch));
            }else throw new RuntimeException("There is nothing you need");
        }
        // Retrieving each single Inventory and putting it into the list<Inventory> for further processing
        List<Inventory>inventories = new ArrayList<>();
        for(List<Inventory> listItem:totalList){
            for(Inventory inventory: listItem){
                inventories.add(inventory);
            }
        }
        System.out.println(inventories + "HERE WE ARE");

//        List<Inventory>inventories = inventoryRepository.findBySkuCodeIn(skuCode);
        List<InventoryResponse> gatheredInventoryResponses = inventories.stream()
                .map(inventory -> new InventoryResponse(inventory.getSkuCode(),
                       inventory.getQuantity() > 0)).collect(Collectors.toList());
        return gatheredInventoryResponses;

    }

    public void addInventoryToDb(InventoryRequestDto inventoryRequestDto){
        inventoryRepository.save(mapFromDtoToInventory(inventoryRequestDto));
        System.out.println("The inventory is added");
    }

    public Inventory mapFromDtoToInventory(InventoryRequestDto inventoryRequestDto){
        return new Inventory(inventoryRequestDto.getSkuCode(),
                inventoryRequestDto.getQuantity());
    }

    public List<Inventory> findAllInventories(){
        return inventoryRepository.findAll();
    }
}
