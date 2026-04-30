package com.spring_example.spring_project_mvc.repo;

import com.spring_example.spring_project_mvc.model.Order;
import com.spring_example.spring_project_mvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>  {

    Optional<Order> findByOrderId(String orderId);
}
