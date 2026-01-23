package goya.daw2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pelicula {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nombre;
	    private String director;
	    private String clasificacion;
	    
		public Pelicula(Long id, String nombre, String director, String clasificacion) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.director = director;
			this.clasificacion = clasificacion;
		}

		public Pelicula() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public String getClasificacion() {
			return clasificacion;
		}

		public void setClasificacion(String clasificacion) {
			this.clasificacion = clasificacion;
		}

		@Override
		public String toString() {
			return "Pelicula [id=" + id + ", nombre=" + nombre + ", director=" + director + ", clasificacion="
					+ clasificacion + "]";
		}
	    
	    
	    
	    

}
