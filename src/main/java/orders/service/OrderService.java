package orders.service;

import orders.model.Order;
import orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // Método para obtener todas las órdenes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Método para obtener una orden por su ID
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Método para crear una nueva orden
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Método para eliminar una orden por su ID
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // Método para actualizar una orden existente
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setStatus(updatedOrder.getStatus());
            existingOrder.setTotal(updatedOrder.getTotal());
            existingOrder.setProductId(updatedOrder.getProductId());
            return orderRepository.save(existingOrder);
        } else {
            throw new IllegalArgumentException("No se encontró la orden con el ID proporcionado");
        }
    }
}
