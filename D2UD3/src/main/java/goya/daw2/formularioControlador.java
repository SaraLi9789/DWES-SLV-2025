package goya.daw2;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class formularioControlador {

    private final String usuario = "sara";
    private final String contraseña = "1234";

    @GetMapping("/")
    public String inicio(HttpSession sesion, Model modelo) {
        if (sesion.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
        return "inicio";
    }

       
    @GetMapping("/pagina1")
    public String pagina1(HttpSession sesion, Model modelo) {
        if (sesion.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
        return "pagina1";
    }

    
    @GetMapping("/pagina2")
    public String pagina2(HttpSession sesion, Model modelo) {
        if (sesion.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
        return "pagina2";
    }

    @GetMapping("/login")
    public String login(HttpSession sesion) {
        if (sesion.getAttribute("usuario") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("usuario") String usuario,
                                @RequestParam("contrasenya") String contrasenya,
                                HttpSession sesion,
                                Model modelo) {

        if ("sara".equals(usuario) && "1234".equals(contrasenya)) {
            sesion.setAttribute("usuario", usuario);
            return "redirect:/";
        } else {
            modelo.addAttribute("error", "Usuario o contraseña incorrectos");
            modelo.addAttribute("Usuario", usuario);
            return "login";
        }
    }
    
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession sesion) {
    	sesion.removeAttribute("usuario");
    	return "redirect:/login";
    }
    
   
}

	


