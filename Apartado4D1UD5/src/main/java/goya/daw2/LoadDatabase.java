package goya.daw2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import goya.daw2.model.Pelicula;
import goya.daw2.repositories.PeliculaRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PeliculaRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Pelicula(null, "El sexto sentido", "M. Night Shyamalan", "Drama")));
            log.info("Preloading " + repository.save(new Pelicula(null, "Pulp Fiction", "Tarantino", "Acción")));
        };
    }
}
