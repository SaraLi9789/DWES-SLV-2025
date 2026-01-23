package goya.daw2;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public class PeliculaRepository {
	@RepositoryRestResource(collectionResourceRel = "pelicula", path = "pelicula")
	public interface PeliculasRepository
	        extends PagingAndSortingRepository<Pelicula, Long> {

	    List<Pelicula> findByNombre(@Param("nombre") String nombre);
	

}
}


