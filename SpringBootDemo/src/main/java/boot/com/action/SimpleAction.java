package boot.com.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import boot.com.entity.User;
import boot.com.mapper.UserMapper;

import org.springframework.jdbc.core.JdbcTemplate;


@Controller
@EnableAutoConfiguration
public class SimpleAction {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Resource(name="secondJdbcTemplate")
	private JdbcTemplate secondJdbcTemplate;
	
	/**
	 * æ≤Ã¨∑√Œ 
	 * @return
	 */
	@GetMapping("/static")
	String demo(){
		System.out.println("---------static--------");
		return "/index.html";
	}
	
	/**
	 * ∂ØÃ¨∑√Œ 
	 * @param model
	 * @return
	 */
	@GetMapping("/templates")
	String templatesDemo(Model model){
		String sql = "select * from st_video_info";
		String sql1 = "select * from user_tb";
		String sql2 = "select * from user_tb where id =1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Map<String, Object> map = secondJdbcTemplate.queryForMap(sql2);
		List<User> users = secondJdbcTemplate.query(sql1, new UserMapper());
		
		System.out.println(list.toString());
		System.out.println(map.toString());
		
		model.addAttribute("user", map);
		model.addAttribute("mv", list);
		model.addAttribute("users", users);
		System.out.println("------------templates-------------");
		return "/index";
	}
	
	@GetMapping("/user")
	String dynamicDemo(Model model){
		model.addAttribute("name", "admin");
		return "/account/user";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleAction.class, args);
	}

}
