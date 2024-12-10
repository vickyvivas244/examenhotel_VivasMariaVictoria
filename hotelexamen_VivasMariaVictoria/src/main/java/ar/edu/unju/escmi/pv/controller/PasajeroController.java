package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.edu.unju.escmi.pv.model.Pasajero;
import ar.edu.unju.escmi.pv.repository.PasajeroRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pasajeros")
public class PasajeroController {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    // Listar todos los pasajeros (con filtro opcional por estado)
    @GetMapping
    public ResponseEntity<List<Pasajero>> listar(@RequestParam(required = false) Boolean estado) {
        List<Pasajero> pasajeros = (estado == null) 
            ? pasajeroRepository.findAll() 
            : pasajeroRepository.findByEstado(estado);
        return ResponseEntity.ok(pasajeros);
    }

    // Crear un pasajero
    @PostMapping
    public ResponseEntity<Pasajero> crear(@Valid @RequestBody Pasajero pasajero) {
        Pasajero nuevoPasajero = pasajeroRepository.save(pasajero);
        return ResponseEntity.ok(nuevoPasajero);
    }

    // Eliminar lógicamente un pasajero
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminar(@PathVariable String dni) {
        Pasajero pasajero = pasajeroRepository.findById(dni)
            .orElseThrow(() -> new RuntimeException("Pasajero no encontrado con el DNI: " + dni));
        pasajero.setEstado(false); // Cambiar el estado a inactivo
        pasajeroRepository.save(pasajero);
        return ResponseEntity.noContent().build();
    }
}
