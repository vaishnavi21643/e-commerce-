package com.spring_example.spring_project_mvc.controller;

import com.spring_example.spring_project_mvc.model.Product;
import com.spring_example.spring_project_mvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){

        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product.getId()>0){
            return new ResponseEntity<>(product, HttpStatus.OK);

        }else{
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProduct(@PathVariable int productId){
        Product product = productService.getProductById(productId);
        if(product.getId()>0){
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product savedProduct = null;
        try {
            savedProduct = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct (@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product updatedProduct = null;
        try{
            updatedProduct = productService.updateProduct(product, imageFile);
            return new ResponseEntity<>("updated", HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> product = productService.searchProduct(keyword);
        System.out.println("searching with: "+keyword);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

}
