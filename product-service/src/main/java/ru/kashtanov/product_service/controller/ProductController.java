package ru.kashtanov.product_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kashtanov.product_service.dto.ProductRequestDto;
import ru.kashtanov.product_service.dto.ProductResponseDto;
import ru.kashtanov.product_service.model.Product;
import ru.kashtanov.product_service.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product_scope")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create_product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestDto productRequestDto){ // it transforms from json ==> java-object
        productService.createProduct(productRequestDto);
    }

    @GetMapping("/get_product_by_id/{id}")
    public Product getProductFromDB(@PathVariable("id")Long productId){
        return productService.findCertainProductById(productId);
    }
    @GetMapping("/single")
    public Product getSingleProduct(){
        return new Product(1L,"Sneakers","chocolate-bar",20.0);
    }

    @GetMapping("/get_all_products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getAllProducts(){
       return productService.findAllProducts();
    }
}
