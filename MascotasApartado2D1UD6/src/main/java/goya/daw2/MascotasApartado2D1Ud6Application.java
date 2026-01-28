package goya.daw2;

import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class MascotasApartado2D1Ud6Application {
	private static final Logger log = (Logger) LoggerFactory.getLogger(MascotasApartado2D1Ud6Application.class);

	public static void main(String[] args) {
		SpringApplication.run(MascotasApartado2D1Ud6Application.class, args);
	}
	
	 @Bean
	 @Profile("!test")
	    public ApplicationRunner run(RestClient.Builder builder) {
		 RestClient restClient = builder
	                .baseUrl("http://petstore-demo-endpoint.execute-api.com")
	                .build();

	        return args -> {
	            Mascota mascota = restClient
	                    .get()
	                    .uri("/petstore/pets/1")
	                    .retrieve()
	                    .body(Mascota.class);

	            log.info(mascota.toString());
	        };
	    }
	 
	
}
