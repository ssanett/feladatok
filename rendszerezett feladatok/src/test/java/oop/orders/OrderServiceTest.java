package oop.orders;

import adatbazis.orders.Order;
import adatbazis.orders.OrderRepository;
import adatbazis.orders.OrderService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService orderService;

    @BeforeEach
    void init() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/zaro?useUnicode=true");
            dataSource.setUser("try");
            dataSource.setPassword("try");
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot connect!", se);
        }

        Flyway flyway = Flyway.configure().cleanDisabled(false).dataSource(dataSource).load();

        flyway.clean();
        flyway.migrate();

        OrderRepository orderRepository = new OrderRepository(dataSource);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void saveOrder() {
        Long id = orderService.saveOrder(new Order("Laptop", 2, 1200));

        assertTrue(id != null);
    }

    @Test
    void findOrdersInAlphabeticOrder() {
        orderService.saveOrder(new Order("Mobile", 1, 800));
        orderService.saveOrder(new Order("Laptop", 2, 1200));
        orderService.saveOrder(new Order("Tv", 1, 150));
        orderService.saveOrder(new Order("Hairdrier", 2, 200));

        List<Order> orders = orderService.getOrders();

        assertEquals(List.of("Hairdrier", "Laptop", "Mobile", "Tv"),
                orders.stream().map(Order::getProductName).toList());
    }

    @Test
    void findMostExpensiveOrderPrice() {
        orderService.saveOrder(new Order("Mobile", 4, 800));
        orderService.saveOrder(new Order("Laptop", 2, 1200));

        assertEquals(3200, orderService.getMostExpensiveOrderPrice());
    }

    @Test
    void testCollectProductsAndCount() {
        orderService.saveOrder(new Order("Mobile", 1, 800));
        orderService.saveOrder(new Order("Laptop", 2, 1200));
        orderService.saveOrder(new Order("Mobile", 3, 1500));
        orderService.saveOrder(new Order("Hairdrier", 2, 200));
        orderService.saveOrder(new Order("Laptop", 4, 1200));

        Map<String, Integer> result = orderService.collectProductsAndCount();

        assertEquals(3, result.keySet().size());
        assertEquals(Set.of("Mobile", "Laptop", "Hairdrier"), result.keySet());
        assertEquals(List.of(2, 4, 6), result.values().stream().sorted().toList());
    }
}