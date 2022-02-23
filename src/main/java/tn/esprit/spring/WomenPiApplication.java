package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication
@EnableWebMvc
@EnableAspectJAutoProxy
public class WomenPiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WomenPiApplication.class, args);
	}

}
