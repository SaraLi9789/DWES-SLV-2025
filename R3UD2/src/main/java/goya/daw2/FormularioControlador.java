package goya.daw2;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class FormularioControlador {

    @GetMapping("/pagina1")
    public String pagina1(Model modelo) {
        modelo.addAttribute("formulario", new Formulario());
        return "pagina1";
    }
    
    @PostMapping("/pagina2")
    public String pagina2(Formulario formulario, Model modelo) {
    	 if (formulario.getNombre().equals("")) {
    	        modelo.addAttribute("Introduce un nombre");
    	        return "pagina1";
    	    }
    	 else {
    		    modelo.addAttribute("formulario", formulario);
    	        return "pagina2";
    	 }
    }

    @PostMapping("/pagina3")
    public String pagina3(Formulario formulario, Model modelo) {
    	if (formulario.getCiudad().equals("")) {
            modelo.addAttribute("Tienes que seleccionar una ciudad");
            return "pagina2";
        }
    	else {
    		modelo.addAttribute("formulario", formulario);
            return "pagina3";
    	}
        
    }

    @PostMapping("/resultado")
    public String resultado(Formulario formulario, Model modelo) {
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("Nombre: " + formulario.getNombre());
        resultados.add("Ciudad: " + formulario.getCiudad());
        resultados.add("Género: " + formulario.getGenero());
        modelo.addAttribute("datos", resultados);
        return "resultado";
    }
}