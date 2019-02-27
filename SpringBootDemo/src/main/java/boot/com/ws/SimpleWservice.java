package boot.com.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import boot.com.entity.User;
import boot.com.mapper.UserMapper;

@Endpoint
@EnableAutoConfiguration
public class SimpleWservice {
	private static final String NAMESPACE = "http://www.boot.com/WebWservice";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PayloadRoot(namespace=NAMESPACE , localPart="simeRequest")
	@ResponsePayload
	String jdbcDemo(){
		String sql = "select * from st_video_info";
		/*List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.toString();*/
		return sql;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleWservice.class, args);
	}
}
