package adatbazis.orders;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public long saveOrder(Order order){
        return orderRepository.saveOrder(order);
    }

    public List<Order> getOrders(){
        return orderRepository.getOrders();
    }

    public int getMostExpensiveOrderPrice(){
        return orderRepository.getMostExpensiveOrderPrice();
    }

    public Map<String,Integer> collectProductsAndCount(){
        Map<String,Integer> result = new HashMap<>();
        List<Order> orders = getOrders();
        for(Order o:orders){
            String name = o.getProductName();
            int count = o.getProductCount();
            if(!result.containsKey(name)){
                result.put(name,0);
            }

            result.put(name,result.get(name)+count);
        }
        return result;
    }
}
