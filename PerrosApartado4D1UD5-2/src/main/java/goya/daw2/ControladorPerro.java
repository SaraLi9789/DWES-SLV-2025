package goya.daw2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControladorPerro {
	 private final ServicioPerro servicioPerro;

	    public ControladorPerro(ServicioPerro servicioPerro) {
	        this.servicioPerro = servicioPerro;
	    }

	    @GetMapping("/perros")
	    public Perros todolosperros() {
	        return servicioPerro.todoslosperros();
	    }

}
