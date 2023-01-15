package adatbazis.message;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Optional;

public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertUser(String username){
        jdbcTemplate.update("insert into users (user_name) values (?)",
                username);
    }

    public Optional<User> findUserByName(String username){
        return jdbcTemplate.query("select * from users where user_name=?",
                ((rs, rowNum) -> new User(rs.getInt("user_id"),rs.getString("user_name"))),username)
                .stream().findAny();
    }
}
