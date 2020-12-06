package innotech.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import innotech.com.services.IUploadFileService;

@SpringBootApplication
public class IvaSpringApplication implements CommandLineRunner{

	@Autowired
	IUploadFileService uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(IvaSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteall();
		uploadFileService.init();
		
		String clave = "12345";
		
		for (int i= 1; i<=2; i++) {
			String passEncoder = passwordEncoder.encode(clave);
			System.out.println(passEncoder);
		}
		
		// String uri = "https://restcountries.eu/rest/v2/capital/{capital}";
	        
		//  SpringApplication.run(WorldStatsApplication.class, args);

	      //  RestTemplate restTemplate = new RestTemplate();
	    //    BaseCountries country = restTemplate.getForObject(uri, BaseCountries.class, "London");

	    //    System.out.println("Country Name: " + country.getCapital());

	        
		
	}

}
