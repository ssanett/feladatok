package oop.orders;

import adatbazis.orders.Order;
import adatbazis.orders.OrderRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    OrderRepository orderRepository;

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

        orderRepository = new OrderRepository(dataSource);
    }

    @Test
    void saveOrder() {
        Long id = orderRepository.saveOrder(new Order("Laptop", 2, 1200));

        assertTrue(id != null);
    }

    @Test
    void findOrdersInAlphabeticOrder() {
        orderRepository.saveOrder(new Order("Mobile", 1, 800));
        orderRepository.saveOrder(new Order("Laptop", 2, 1200));
        orderRepository.saveOrder(new Order("Tv", 1, 150));
        orderRepository.saveOrder(new Order("Hairdrier", 2, 200));

        List<Order> orders = orderRepository.getOrders();

        assertEquals(List.of("Hairdrier", "Laptop", "Mobile", "Tv"),
                orders.stream().map(Order::getProductName).toList());
    }

    @Test
    void findMostExpensiveOrderPrice() {
        orderRepository.saveOrder(new Order("Mobile", 4, 800));
        orderRepository.saveOrder(new Order("Laptop", 2, 1200));

        assertEquals(3200, orderRepository.getMostExpensiveOrderPrice());
    }
}