package ru.kashtanov.product_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kashtanov.product_service.controller.ProductController;
import ru.kashtanov.product_service.dto.ProductRequestDto;
import ru.kashtanov.product_service.dto.ProductResponseDto;
import ru.kashtanov.product_service.model.Product;
import ru.kashtanov.product_service.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private static final Logger logInfo = LoggerFactory.getLogger(ProductController.class);


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        productRepository.save(product);
        logInfo.info("The product is successfully created "+ product.getId());
    }

    public Product findCertainProductById(Long productId){
       return productRepository.findById(productId).orElseThrow();
    }
    public List<ProductResponseDto> findAllProducts(){
        List<Product>products = productRepository.findAll();
        return products.stream().map(product -> mapToProductResponseDto(product)).collect(Collectors.toList());
    }

    public ProductResponseDto mapToProductResponseDto(Product product){
        return  new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
