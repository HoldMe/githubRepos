package boot.edden.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boot.eden.entity.User;
import boot.eden.service.RemoteSpringServiceApi;

/**
* @author Eden
* @version 创建时间：2019年1月14日 下午6:13:02
* 远程接口实现
*/
@RestController
public class RemoteSpringServiceApiImpl implements RemoteSpringServiceApi{

	public User hello(@RequestParam("name") String name) {
		User user = new User();
		user.setName(name);
		return user;
	}

	public User hello(@RequestParam("name") String name, @RequestParam("age") Integer age) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return user;
	}

	public User hello(@RequestBody User user) {
		if(user==null){
			user = new User();
			user.setName("it's a empty user");
		}
		return user;
	}

}
