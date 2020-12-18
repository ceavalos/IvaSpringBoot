package innotech.com.configuration;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	
@PropertySource("classpath:configuraciones.properties")
public class PropertiesSource{

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/*
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		String resourcesPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		
		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcesPath);
		
		log.info("resourcesPath: "+resourcesPath);

	}*/

	
	
}
