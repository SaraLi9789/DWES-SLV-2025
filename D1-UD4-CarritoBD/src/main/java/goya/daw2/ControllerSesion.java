package goya.daw2;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControllerSesion {
	//private final RepositorioQuizz repositorio;
	private final personaRepository personaRepositorio;
	private final PartidaRepository partidaRepositorio;
	
	public ControllerSesion(personaRepository personaRepositorio,PartidaRepository partidaRepositorio ) {
		super();
		this.personaRepositorio = personaRepositorio;
		this.partidaRepositorio = partidaRepositorio;
	}

	static final String[] SIGNOS = { "", "Aries", "Tauro", "Géminis", "Cáncer", "Leo", "Virgo", "Libra", "Escorpio",
			"Sagitario", "Capricornio", "Acuario", "Piscis" };
	static final String[] AFICCIONES = { "Deportes", "Juerga", "Lectura", "Relaciones sociales" };

	@PostMapping("/")
	String procesaEtapaXSesion(@RequestParam(name = "numEtapa") Integer numEtapa,
			@RequestParam(name = "aficciones", required = false) String aficciones,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "signo", required = false) String signo, Model modelo,
			HttpSession sesion) {
		
		        if (nombre == null || nombre.isBlank()) {
		            nombre = (String) sesion.getAttribute("nombre");
		        }

		        if (signo == null || signo.isBlank()) {
		            signo = (String) sesion.getAttribute("signo");
		        }

		        if (aficciones == null || aficciones.isBlank()) {
		            aficciones = (String) sesion.getAttribute("aficciones");
		        }
		
		
			
		if(nombre != null && !nombre.isBlank()) {
			sesion.setAttribute("nombre", nombre);
		}
		
		if (signo != null && !signo.isBlank()) {
			sesion.setAttribute("signo", signo);

		}

		if (aficciones != null && !aficciones.isBlank()) {
			sesion.setAttribute("aficciones", aficciones);

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
			errores = "Debes elegir al menos una aficción";
		}

		if (!errores.isBlank()) {
			modelo.addAttribute("errores", errores);
			modelo.addAttribute("numEtapa", numEtapa);
			return "etapa" + numEtapa;
		}

		numEtapa++;

		modelo.addAttribute("numEtapa", numEtapa);
		
		

		if (numEtapa == 4) {
			int puntuacion = 0;
			
			if(signo != null && !signo.isBlank()) {
				puntuacion++;
			}
			if(aficciones != null && !aficciones.isBlank()) {
				puntuacion++;
			}
			
			Persona persona = new Persona(nombre);
			personaRepositorio.save(persona);
			Categoria categoria = saberCategoria(puntuacion);

			Partida partida = new Partida(puntuacion, new Date(), categoria, persona);
			partidaRepositorio.save(partida);
			
			
			ArrayList<String> respuestas = new ArrayList<String>();
			respuestas.add(nombre);
			respuestas.add(SIGNOS[Integer.parseInt(signo)]);
			respuestas.add(aficciones);
			modelo.addAttribute("respuestas", respuestas);
			modelo.addAttribute("puntuacion", puntuacion);
			modelo.addAttribute("categoria", categoria);
		}

		return "etapa" + numEtapa;
		
	}
	
	public Categoria saberCategoria(int puntuacion) {
		if(puntuacion >= 0 && puntuacion <= 10) {
			return Categoria.baja;
		}
		if(puntuacion >= 11 && puntuacion <= 20) {
			return Categoria.media;
		}
		
		return Categoria.alta;
		
	}

	@GetMapping("/")
	String getEtapa0() {
		return "etapa1";
	}

}
