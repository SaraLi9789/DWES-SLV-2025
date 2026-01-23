package goya.daw2.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import goya.daw2.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    Optional<Pelicula> findByNombreAndDirector(String nombre, String director);
}

