package adatbazis.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;

public class OrderRepository {

    private JdbcTemplate jdbcTemplate;

    public OrderRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long saveOrder(Order order){
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into orders (product_name,product_count,product_price) values(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
                     ps.setString(1,order.getProductName());
                     ps.setInt(2,order.getProductCount());
                     ps.setInt(3,order.getPricePerProduct());
                     return ps;
        },holder);
        return holder.getKey().intValue();
    }

    public List<Order> getOrders(){
        return jdbcTemplate.query("select * from orders",
                ((rs, rowNum) -> new Order(rs.getInt("id"),rs.getString("product_name"),rs.getInt("product_count"),rs.getInt("product_price")))
                ).stream().sorted(Comparator.comparing(Order::getProductName)).toList();
    }

    public int getMostExpensiveOrderPrice(){
        return jdbcTemplate.queryForObject("select max(product_count * product_price) from orders",
                (rs, rowNum) -> rs.getInt(1));
    }
}
