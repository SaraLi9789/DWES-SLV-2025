package goya.daw2;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormularioControlador {
	
	@GetMapping("/")
	public String inicio() {
		return "formularioPost";
	}

	@PostMapping("/enviarPost")
	public String enviarPorPost(@RequestParam(name= "texto") String texto, Model modelo) {
		modelo.addAttribute("texto", texto);
		return "resultado";
		
	}
	
	@GetMapping("/formularioGet")
	public String formularioGet() {
		return "formularioGet";
	}
	
	@GetMapping("/enviarGet")
	public String enviarPorGet(@RequestParam(name= "texto") String texto, Model modelo) {
		modelo.addAttribute("texto", texto);
		return "resultado";
	}
	
	@GetMapping("/enlace")
	public String enlazar(@RequestParam(name="texto") String texto, Model modelo) {
		modelo.addAttribute("texto", texto);
		return "resultado";
	}
	
	@GetMapping("/pruebaEnlace")
	public String codificar(Model modelo) {
		String texto = "SaraLiñánVázquez%\\&$#!\\´``\"";
		String textoCodificar = URLEncoder.encode(texto, StandardCharsets.UTF_8);
		modelo.addAttribute("textoCodificado", "/enlace?texto=" + textoCodificar);
		return "resultado";
	}
	
}


/*	 @Autowired
private MessageSource messageSource;

@GetMapping("/")
public String inicio(Model modelo, Locale locale) {
    // Obtenemos el mensaje según el idioma detectado
    String mensaje = messageSource.getMessage("saludar", null, "Mensaje no encontrado", locale);
    modelo.addAttribute("mensaje", mensaje);
    return "formularioPost";
}
*/
