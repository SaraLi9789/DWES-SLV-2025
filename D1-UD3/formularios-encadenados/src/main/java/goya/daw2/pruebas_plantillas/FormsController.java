package goya.daw2.pruebas_plantillas;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FormsController {

	static final String[] SIGNOS = { "", "Aries", "Tauro", "Géminis", "Cáncer", "Leo", "Virgo", "Libra", "Escorpio",
			"Sagitario", "Capricornio", "Acuario", "Piscis" };
	static final String[] AFICCIONES = { "Deportes", "Juerga", "Lectura", "Relaciones sociales" };

	@PostMapping("/")
	String procesaEtapaX(@RequestParam(name = "numEtapa") Integer numEtapa,
			@RequestParam(name = "aficciones", required = false) String aficciones,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "signo", required = false) String signo, Model modelo,
			HttpServletRequest peticion, HttpServletResponse respuesta) {
		
		Cookie[] cookies = peticion.getCookies();
		if (cookies != null) {
		    for (int i = 0; i < cookies.length; i++) {
		        Cookie cookie = cookies[i];

		        if (cookie.getName().equals("nombre") && (nombre == null || nombre.isBlank())) {
		            nombre = cookie.getValue();
		        }

		        if (cookie.getName().equals("signo") && (signo == null || signo.isBlank())) {
		            signo = cookie.getValue();
		        }

		        if (cookie.getName().equals("aficciones") && (aficciones == null || aficciones.isBlank())) {
		            aficciones = cookie.getValue();
		        }
		    }
		
		}
		
		
			
		if(nombre != null && !nombre.isBlank()) {
			Cookie nombreC = new Cookie("nombre", nombre);
			respuesta.addCookie(nombreC);
		}
		
		if (signo != null && !signo.isBlank()) {
		    Cookie signoC = new Cookie("signo", signo);
		    respuesta.addCookie(signoC);
		}

		if (aficciones != null && !aficciones.isBlank()) {
		    Cookie aficcionesC = new Cookie("aficciones", aficciones);
		    respuesta.addCookie(aficcionesC);
		}
		
		
		

		if (numEtapa == null)
			return "etapa1";
		if (nombre != null) {
			modelo.addAttribute("nombre", nombre);
		}

		modelo.addAttribute("signos", SIGNOS);
		modelo.addAttribute("aficciones", AFICCIONES);

		if (signo != null) {
			modelo.addAttribute("signo", signo);
		}

		String errores = "";

		if (numEtapa == 1 && (nombre == null || nombre.isBlank())) {
			errores = "Debes poner un nombre no vacío";
		} else if (numEtapa == 1 && (nombre.length() < 3 || nombre.length() > 10)) {
			errores = "La longitud del nombre debe estar entre 3 y 10";
		}

		if (numEtapa == 2 && (signo == null || signo.equals("0"))) {
			errores = "Debes seleccionar un signo";
		}

		if (numEtapa == 3 && (aficciones == null || aficciones.isBlank())) {
			errores = "Debes elegir al menos una aficción, no seas soso/a";
		}

		if (!errores.isBlank()) {
			modelo.addAttribute("errores", errores);
			modelo.addAttribute("numEtapa", numEtapa);
			return "etapa" + numEtapa;
		}

		numEtapa++;

		modelo.addAttribute("numEtapa", numEtapa);

		if (numEtapa == 4) {
			ArrayList<String> respuestas = new ArrayList<String>();
			respuestas.add(nombre);
			respuestas.add(SIGNOS[Integer.parseInt(signo)]);
			respuestas.add(aficciones);
			modelo.addAttribute("respuestas", respuestas);
		}

		return "etapa" + numEtapa;
	}

	@GetMapping("/")
	String getEtapa0() {
		return "etapa1";
	}

}
