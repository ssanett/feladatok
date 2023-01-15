package oop.orders;

import adatbazis.orders.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testCreateOrder() {
        Order order = new Order("laptop", 1, 1000);

        assertEquals("laptop", order.getProductName());
        assertEquals(1, order.getProductCount());
        assertEquals(1000, order.getPricePerProduct());
    }
}