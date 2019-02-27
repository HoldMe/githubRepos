package boot.eden.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import boot.eden.entity.User;

/**
* @author Eden
* @version 创建时间：2019年1月14日 下午6:00:29
* 接口api
*/
@RequestMapping("/hello-service")
public interface RemoteSpringServiceApi {

	@RequestMapping(value="hello1",method = RequestMethod.GET)
	public User hello(@RequestParam("name") String name);
	
	@RequestMapping(value="hello2",method = RequestMethod.GET)
	public User hello(@RequestParam("name") String name,@RequestParam("age") Integer age);
	
	@RequestMapping(value="hello3",method = RequestMethod.POST)
	public User hello(@RequestBody User user);
}
