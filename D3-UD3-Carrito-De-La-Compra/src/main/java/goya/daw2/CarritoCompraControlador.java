package goya.daw2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class CarritoCompraControlador {
	
	private RepositorioStockEnTxt carrito = new RepositorioStockEnTxt();
	
	@GetMapping("/inicio")
	public String inicio() {
		return "inicio";
	}
	
	
	@GetMapping("/")
	public String nombre() {
		return "nombre";
	}
	
	@PostMapping("/nombre")
    public String guardarNombre(@RequestParam("nombre") String nombre, HttpSession sesion) {
        sesion.setAttribute("nombre", nombre);
        return "redirect:/inicio";
    }
	
	@GetMapping("/productos")
		public String productos(Model modelo, HttpSession sesion) {
          modelo.addAttribute("nombre", sesion.getAttribute("nombre"));
			modelo.addAttribute("stock", carrito.getAll());
			
			Map<String, Integer> carrito = (Map<String, Integer>) sesion.getAttribute("carrito");
			if (carrito == null) {
				carrito = new HashMap<>();
				sesion.setAttribute("carrito", carrito);
			}
			
			modelo.addAttribute("carrito",carrito);
			return "productos";
		}
	
	@PostMapping("/actualizar")
	public String actualizarProductos(@RequestParam("productos") String productos[],
			@RequestParam("cantidades") String cantidad[],
			HttpSession sesion, Model modelo) {
		Map<String, Integer> Carrosesion = (Map<String, Integer>) sesion.getAttribute("carrito");
		if (Carrosesion == null) {
			Carrosesion = new HashMap<>();
			sesion.setAttribute("carrito", Carrosesion);
		}
		
	    Map<String, Integer> stock = carrito.getAll();
	    for(int i = 0; i < productos.length; i++) {
	    	String productoos = productos[i];
	    	int numero = 0;
	    	if(cantidad[i] != null && !cantidad[i].isEmpty()) {
	    		numero = Integer.parseInt(cantidad[i]);
	    	}
	    
	    	
	    	int disponible = stock.get(productoos);
	    	if(numero > disponible) {
	    		numero = disponible;
	    	}
	    	
	    	Carrosesion.put(productoos, numero);
	    }
		
	    sesion.setAttribute("carrito", Carrosesion);
	    modelo.addAttribute("stock", stock);
	    modelo.addAttribute("carrito", Carrosesion);

		return "productos";
		
	}
	
	@PostMapping("/finalizar")
	public String finalizarProductos(@RequestParam("productos") String productos[],
			@RequestParam("cantidades") String cantidad[],
			HttpSession sesion, Model modelo) {
		Map<String, Integer> Carrosesion = (Map<String, Integer>) sesion.getAttribute("carrito");
		if (Carrosesion == null) {
			Carrosesion = new HashMap<>();
			sesion.setAttribute("carrito", Carrosesion);
		}
	    Map<String, Integer> stock = carrito.getAll();
	    for(int i = 0; i < productos.length; i++) {
	    	String producto = productos[i];
	    	int numero = 0;
	    	if(cantidad[i] != null && !cantidad[i].isEmpty()) {
	    		numero = Integer.parseInt(cantidad[i]);
	    	}
	    	int disponible = stock.get(producto);
	    	if(numero > disponible) {
	    		modelo.addAttribute("stock", stock);
	    		modelo.addAttribute("carrito", Carrosesion);
	    		modelo.addAttribute("error", "No hay stock");
	    	}	
	    }
	    
	    for(int i = 0; i < productos.length; i++) {
	    	 String producto = productos[i];
	         int numero = 0;

	         if (cantidad[i] != null && !cantidad[i].isEmpty()) {
	             numero = Integer.parseInt(cantidad[i]);
	         }

	         int nuevoStock = stock.get(producto) - numero;
	         carrito.modify(producto, nuevoStock); 
	     }

	     modelo.addAttribute("stock", carrito.getAll());
	     modelo.addAttribute("carrito", Carrosesion);
	     modelo.addAttribute("mensaje", "Es correcto");


			return "productos";
	}
	
	@PostMapping("/reponerStock")
	public String reponerStock(@RequestParam (name = "producto", required = false) String producto,
    @RequestParam(name = "cantidad", required = false) Integer cantidad,
    Model modelo) {
	
	    	carrito.modify(producto, cantidad);
	    	 modelo.addAttribute("stock", carrito.getAll());
	        modelo.addAttribute("mensaje", producto + " actualizado a " + cantidad);
	 		return "reponerStock";

	    }
	   
		
	}
	
