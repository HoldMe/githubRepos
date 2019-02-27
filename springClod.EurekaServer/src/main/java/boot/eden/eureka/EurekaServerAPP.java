package boot.eden.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
* @author Eden
* @version 创建时间：2018年11月20日 下午2:32:49
*/

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerAPP {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerAPP.class, args);
	}
}
