package innotech.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import innotech.com.services.IUploadFileService;

@SpringBootApplication
public class IvaSpringApplication implements CommandLineRunner{

	@Autowired
	IUploadFileService uploadFileService;
	
	public static void main(String[] args) {
		SpringApplication.run(IvaSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteall();
		uploadFileService.init();
		
		
		// String uri = "https://restcountries.eu/rest/v2/capital/{capital}";
	        
		//  SpringApplication.run(WorldStatsApplication.class, args);

	      //  RestTemplate restTemplate = new RestTemplate();
	    //    BaseCountries country = restTemplate.getForObject(uri, BaseCountries.class, "London");

	    //    System.out.println("Country Name: " + country.getCapital());

	        
		
	}

}
