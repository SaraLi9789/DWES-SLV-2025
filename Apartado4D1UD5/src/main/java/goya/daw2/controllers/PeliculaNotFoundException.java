package goya.daw2.controllers;

public class PeliculaNotFoundException extends RuntimeException{
	    public PeliculaNotFoundException(Long id) {
	        super("No se encontró la película con id: " + id);
	    }

}
