package goya.daw2;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Partida {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int puntuacion;
	private Date fecha;
	
	public Partida() {
		super();
	}

	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@ManyToOne
	private Persona persona;
	
	public Partida(int puntuacion, Date fecha, Categoria categoria, Persona persona) {
		super();
		this.puntuacion = puntuacion;
		this.fecha = new Date();
		this.categoria = categoria;
		this.persona = persona;
	}
	
}
