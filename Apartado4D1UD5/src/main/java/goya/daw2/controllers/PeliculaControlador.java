package goya.daw2.controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goya.daw2.model.Pelicula;
import goya.daw2.repositories.PeliculaRepository;

@RestController
@RequestMapping("/pelicula")
public class PeliculaControlador {

    private final PeliculaRepository repository;

    public PeliculaControlador(PeliculaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> one(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<Pelicula> newPelicula(@RequestBody Pelicula nueva) {
        if (repository.findByNombreAndDirector(nueva.getNombre(), nueva.getDirector()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Pelicula saved = repository.save(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> replacePelicula(@RequestBody Pelicula nueva, @PathVariable Long id) {
        return repository.findById(id)
                .map(pelicula -> {
                    pelicula.setNombre(nueva.getNombre());
                    pelicula.setDirector(nueva.getDirector());
                    pelicula.setClasificacion(nueva.getClasificacion());
                    return ResponseEntity.ok(repository.save(pelicula));
                })
                .orElseGet(() -> {
                    Pelicula saved = repository.save(nueva);
                    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
                });
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pelicula> patchPelicula(@RequestBody Pelicula nueva, @PathVariable Long id) {
        return repository.findById(id)
                .map(pelicula -> {
                    if (nueva.getNombre() != null) pelicula.setNombre(nueva.getNombre());
                    if (nueva.getDirector() != null) pelicula.setDirector(nueva.getDirector());
                    if (nueva.getClasificacion() != null) pelicula.setClasificacion(nueva.getClasificacion());
                    return ResponseEntity.ok(repository.save(pelicula));
                })
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        return repository.findById(id)
                .map(pelicula -> {
                    repository.delete(pelicula);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }
}

