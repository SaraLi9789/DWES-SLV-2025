package goya.daw2;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ServicioPerro {
    private final RestClient restClient;
    
    public ServicioPerro(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://dog.ceo/api")
                .build();
    }

    public Perros todoslosperros() {
        return restClient
                .get()
                .uri("/breeds/list/all")
                .retrieve()
                .body(Perros.class);
    }

}
