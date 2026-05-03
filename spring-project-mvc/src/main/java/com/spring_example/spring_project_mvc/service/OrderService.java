//package com.spring_example.spring_project_mvc.service;
//
//import com.spring_example.spring_project_mvc.model.Order;
//import com.spring_example.spring_project_mvc.model.OrderItem;
//import com.spring_example.spring_project_mvc.model.Product;
//import com.spring_example.spring_project_mvc.model.dto.OrderItemRequest;
//import com.spring_example.spring_project_mvc.model.dto.OrderItemResponse;
//import com.spring_example.spring_project_mvc.model.dto.OrderRequest;
//import com.spring_example.spring_project_mvc.model.dto.OrderResponse;
//import com.spring_example.spring_project_mvc.repo.OrderRepo;
//import com.spring_example.spring_project_mvc.repo.ProductRepo;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private final ProductRepo productRepo;
//    private final OrderRepo orderRepo;
//
//    @Transactional
//    public OrderResponse placeOrder(OrderRequest request) {
//        Order order = new Order();
//        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
//        order.setOrderId(orderId);
//        order.setCustomerName(request.customerName());
//        order.setEmail(request.email());
//        order.setStatus("PLACED");
//        order.setOrderDate(LocalDate.now());
//
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        for(OrderItemRequest itemReq : request.items()){
//
//            Product product = productRepo.findById(itemReq.productId())
//                    .orElseThrow( ()-> new RuntimeException("Product not found"));
//
////            product.setStockQuantity(itemReq.quantity() - itemReq.quantity());
//            int updatedStock = product.getStockQuantity() - itemReq.quantity();
//
//            if (updatedStock < 0) {
//                throw new RuntimeException("Insufficient stock for product: " + product.getName());
//            }
//
//            product.setStockQuantity(updatedStock);
//            productRepo.save(product);
//            productRepo.save(product);
//
//            OrderItem orderItem = OrderItem.builder()
//                    .product(product)
//                    .quantity(itemReq.quantity())
////                    .totalPrice(product.getPrice())
////                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())))
//                    .totalPrice(product.getPrice()
//                            .multiply(BigDecimal.valueOf(itemReq.quantity())))
//                    .order(order)
//                    .build();
//
//            orderItems.add(orderItem);
//
//
//        }
//
//        order.setOrderItems(orderItems);
//         Order savedOrder = orderRepo.save(order);
//
//         List<OrderItemResponse> itemResponses = new ArrayList<>();
//
//         for(OrderItem item : order.getOrderItems()){
//             OrderItemResponse orderItemResponse = new OrderItemResponse(
//                     item.getProduct().getName(),
//                     item.getQuantity(),
//                     item.getTotalPrice()
//             );
//             itemResponses.add(orderItemResponse);
//
//         }
//
//         OrderResponse orderResponse = new OrderResponse(
//                 savedOrder.getOrderId(),
//                 savedOrder.getCustomerName(),
//                 savedOrder.getEmail(),
//                 savedOrder.getStatus(),
//                 savedOrder.getOrderDate(),
//                 itemResponses
//                 );
//
//         return orderResponse;
//
//    }
//
//
//
////
////    public List<OrderResponse> getAllOrderResponses() {
////        List<Order> orders  = orderRepo.findAll();
////        List<OrderResponse> orderResponses = new ArrayList<>();
////
////        for(Order order : orders ){
////
////            List<OrderItemResponse> itemResponses = new ArrayList<>();
////            for(OrderItem item : order.getOrderItems()){
////                OrderItemResponse orderItemResponse = new OrderItemResponse(
////                        item.getProduct().getName(),
////                        item.getQuantity(),
////                        item.getTotalPrice()
////                );
////                itemResponses.add(orderItemResponse);
////            }
////
////            OrderResponse orderResponse = new OrderResponse(
////                    order.getOrderId(),
////                    order.getOrderId(),
////                    order.getCustomerName(),
////                    order.getEmail(),
////                    order.getStatus(),
////                    order.getOrderDate(),
////                    itemResponses
////
////            );
////            orderResponse.add(orderResponse);
////
////
////
////
////        }
////        return orderResponses;
////    }
//
//
//
//    @Transactional
//    public List<OrderResponse> getAllOrderResponses() {
//
//        List<Order> orders = orderRepo.findAll();
//        List<OrderResponse> orderResponses = new ArrayList<>();
//
//        for (Order order : orders) {
//
//
//            List<OrderItemResponse> itemResponses = new ArrayList<>();
//
//            for(OrderItem item : order.getOrderItems()) {
//                OrderItemResponse orderItemResponse = new OrderItemResponse(
//                        item.getProduct().getName(),
//                        item.getQuantity(),
//                        item.getTotalPrice()
//                );
//                itemResponses.add(orderItemResponse);
//
//            }
//            OrderResponse orderResponse = new OrderResponse(
//                    order.getOrderId(),
//                    order.getCustomerName(),
//                    order.getEmail(),
//                    order.getStatus(),
//                    order.getOrderDate(),
//                    itemResponses
//            );
//            orderResponses.add(orderResponse);
//        }
//
//        return orderResponses;
//    }
//
//
//
//}


package com.spring_example.spring_project_mvc.service;

import com.spring_example.spring_project_mvc.model.Order;
import com.spring_example.spring_project_mvc.model.OrderItem;
import com.spring_example.spring_project_mvc.model.Product;
import com.spring_example.spring_project_mvc.model.User;
import com.spring_example.spring_project_mvc.model.dto.OrderItemRequest;
import com.spring_example.spring_project_mvc.model.dto.OrderItemResponse;
import com.spring_example.spring_project_mvc.model.dto.OrderRequest;
import com.spring_example.spring_project_mvc.model.dto.OrderResponse;
import com.spring_example.spring_project_mvc.repo.OrderRepo;
import com.spring_example.spring_project_mvc.repo.ProductRepo;
import com.spring_example.spring_project_mvc.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    @Autowired
    public OrderService(ProductRepo productRepo, OrderRepo orderRepo, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = new Order();

//latset changes for authentication nd authorization
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setUser(user);

//----------------------------------------------------------------------------------------


        String orderId = "ORD" + UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase();

        order.setOrderId(orderId);
        order.setCustomerName(request.customerName());
        order.setEmail(request.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemReq : request.items()) {

            Product product = productRepo.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int updatedStock = product.getStockQuantity() - itemReq.quantity();
            if (updatedStock < 0) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getName()
                );
            }

            product.setStockQuantity(updatedStock);
            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemReq.quantity())
                    .totalPrice(
                            product.getPrice()
                                    .multiply(BigDecimal.valueOf(itemReq.quantity()))
                    )
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for (OrderItem item : savedOrder.getOrderItems()) {
            itemResponses.add(
                    new OrderItemResponse(
                            item.getProduct().getName(),
                            item.getQuantity(),
                            item.getTotalPrice()
                    )
            );
        }

        return new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                itemResponses
        );
    }

    @Transactional
    public List<OrderResponse> getAllOrderResponses() {

        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {

            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for (OrderItem item : order.getOrderItems()) {
                itemResponses.add(
                        new OrderItemResponse(
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getTotalPrice()
                        )
                );
            }

            orderResponses.add(
                    new OrderResponse(
                            order.getOrderId(),
                            order.getCustomerName(),
                            order.getEmail(),
                            order.getStatus(),
                            order.getOrderDate(),
                            itemResponses
                    )
            );
        }

        return orderResponses;
    }
}
