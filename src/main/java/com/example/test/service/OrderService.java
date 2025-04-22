package com.example.test.service;

import com.example.test.dto.OrderDto;
import com.example.test.dto.OrderItemDto;
import com.example.test.model.Order;
import com.example.test.model.OrderItem;
import com.example.test.model.Product;
import com.example.test.repository.OrderRepository;
import com.example.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// ...

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // ✅ Nouvelle méthode avec DTO
    public Order saveOrderFromDto(OrderDto orderDto) {
        Order order = new Order();
        order.setFirstName(orderDto.getFirstName());
        order.setLastName(orderDto.getLastName());
        order.setEmail(orderDto.getEmail());
        order.setPhone(orderDto.getPhone());
        order.setAddress(orderDto.getAddress());
        order.setPostalCode(orderDto.getPostalCode());
        order.setCity(orderDto.getCity());
        order.setUserId(orderDto.getUserId());
        order.setStatus("pending");

        List<OrderItem> items = orderDto.getItems().stream().map(itemDto -> {
            OrderItem item = new OrderItem();
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'ID : " + itemDto.getProductId()));
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setOrder(order); // très important
            return item;
        }).toList();

        order.setItems(items);

        return orderRepository.save(order);
    }
}
