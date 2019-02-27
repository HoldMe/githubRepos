package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
public class EurekaProviderApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaProviderApp.class, args);
    }
    
   /* @Value("${server.port}")
    String port;
    
    @RequestMapping("/")
    public String sayHello(){
    	return "hello from FromClientOne port: "+port;
    }*/
}
