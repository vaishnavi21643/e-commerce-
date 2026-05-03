package com.spring_example.spring_project_mvc.controller;

import com.spring_example.spring_project_mvc.model.CartItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
//@CrossOrigin

public class CartController {

    // Temporary in-memory cart
    private List<CartItem> cartItems = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems() {
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody CartItem item) {
        cartItems.add(item);
        return new ResponseEntity<>("Item added to cart", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> clearCart() {
        cartItems.clear();
        return new ResponseEntity<>("Cart cleared", HttpStatus.OK);
    }
}