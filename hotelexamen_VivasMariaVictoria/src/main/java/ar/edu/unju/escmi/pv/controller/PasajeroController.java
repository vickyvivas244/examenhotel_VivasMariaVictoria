package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unju.escmi.pv.model.Pasajero;
import ar.edu.unju.escmi.pv.repository.PasajeroRepository;

import java.util.List;

@RestController
@RequestMapping("/pasajeros")
public class PasajeroController {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    // Listar todos los pasajeros (incluyendo filtrado por estado)
    @GetMapping
    public List<Pasajero> listar(@RequestParam(required = false) Boolean estado) {
        if (estado == null) {
            return pasajeroRepository.findAll();
        }
        return pasajeroRepository.findByEstado(estado);
    }

    // Crear un pasajero
    @PostMapping
    public Pasajero crear(@RequestBody Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    // Eliminar lógicamente un pasajero
    @DeleteMapping("/{dni}")
    public void eliminar(@PathVariable String dni) {
        Pasajero pasajero = pasajeroRepository.findById(dni).orElseThrow(() -> 
            new RuntimeException("Pasajero no encontrado."));
        pasajero.setEstado(false); // Cambiar el estado a inactivo
        pasajeroRepository.save(pasajero);
    }
}
