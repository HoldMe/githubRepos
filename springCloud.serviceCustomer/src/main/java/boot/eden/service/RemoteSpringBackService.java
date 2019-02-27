package boot.eden.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
* @author Eden
* @version 创建时间：2019年1月14日 下午6:22:57
* 类说明
*/
@FeignClient(value = "Eureka-service-provider")
public interface RemoteSpringBackService extends RemoteSpringServiceApi{

}
