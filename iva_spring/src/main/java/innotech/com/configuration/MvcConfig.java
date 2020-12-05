package innotech.com.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	
	public void AddViewControllers( ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error_403");
	}
}
