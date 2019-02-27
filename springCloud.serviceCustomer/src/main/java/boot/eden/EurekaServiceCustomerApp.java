package boot.eden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Logger;

/**
* @author Eden
* @version 创建时间：2019年1月14日 下午1:46:37
* 类说明
*/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
public class EurekaServiceCustomerApp {
	
	@Bean
    Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceCustomerApp.class, args);
	}
}
