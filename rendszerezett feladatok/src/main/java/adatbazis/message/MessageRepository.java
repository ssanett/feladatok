package adatbazis.message;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MessageRepository {


    private JdbcTemplate jdbcTemplate;

    public MessageRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertMessage(long sender_id,long receiver_id,String message){
        jdbcTemplate.update("insert into messages (sender_id,receiver_id,message) values (?,?,?)",
                sender_id,receiver_id,message);
    }

    public List<String> findMessagesBySenderId(long sender_id){
        return jdbcTemplate.query("select message from messages where sender_id=?",
                ((rs, rowNum) -> rs.getString("message")),sender_id).stream().toList();
    }


}
