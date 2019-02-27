package boot.eden.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import boot.eden.entity.User;
import boot.eden.service.RemoteSpringBackService;

/**
* @author Eden
* @version 创建时间：2019年1月14日 下午1:48:50
* 测试服务调用
*/
@RestController
public class MyController {
	
	@Autowired
	private RemoteSpringBackService remoteSpringBackService;
	
	@Bean
	@LoadBalanced
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	@RequestMapping(value="/service",method=RequestMethod.GET)
	public String getServiceInfo(){
		RestTemplate restTemplate = getRestTemplate();
		String info = restTemplate.getForObject("http://Eureka-service-provider/api/12", String.class);
		return info;
	}
	
	/*************************************************************************/
	
	
	@RequestMapping(value="/remoteUser")
	public Map<String, User> getUser(){
		Map<String, User> map = new HashMap<String, User>();
		map.put("user1", remoteSpringBackService.hello("张三"));
		map.put("user2", remoteSpringBackService.hello("李四",19));
		map.put("user3", remoteSpringBackService.hello(new User()));
		return map;
	}

}
