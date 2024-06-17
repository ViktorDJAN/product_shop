package ru.kashtanov.inventory_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kashtanov.inventory_service.dto.InventoryRequestDto;
import ru.kashtanov.inventory_service.dto.InventoryResponse;
import ru.kashtanov.inventory_service.model.Inventory;
import ru.kashtanov.inventory_service.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory_scope")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/single")
    @ResponseStatus(HttpStatus.OK)
    public Inventory getSingleInventory() {
        return new Inventory(1L, "skuINVENTORYCode", 11);
    }

    @PostMapping("/create_inventory")
    public void createInventory(@RequestBody InventoryRequestDto inventoryRequestDto) {
        inventoryService.addInventoryToDb(inventoryRequestDto);
    }

    @GetMapping("/is_in_stock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam String skuCode) {
        return inventoryService.isInStock(skuCode);
    }



    @GetMapping("/list_is_in_stock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> listIsInStock(@RequestParam List<String> skuCode) {
        return inventoryService.listIsInStockInto(skuCode);
    }

    @GetMapping("/all")
    public List<Inventory> getAllInventories() {
        return inventoryService.findAllInventories();
    }
}
