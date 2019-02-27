package boot.edden.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* @author Eden
* @version 创建时间：2019年1月14日 下午1:57:40
* 测试服务发布
*/
@RestController
public class MyController {

	@RequestMapping(value="/api/{serviceId}",method=RequestMethod.GET)
	public String servicePubApi(@PathVariable(value="serviceId") Integer serviceId){
		return "Eureka-service-provider: serviceId: "+serviceId;
	}
}
