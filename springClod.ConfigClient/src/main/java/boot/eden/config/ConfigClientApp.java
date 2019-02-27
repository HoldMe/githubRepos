package boot.eden.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ConfigClientApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConfigClientApp.class, args);
    }
    
    @Value("${hello}")
    String hello;
    
    @RequestMapping(value="/hello")
    public String sayHello(){
    	return hello;
    }
}
