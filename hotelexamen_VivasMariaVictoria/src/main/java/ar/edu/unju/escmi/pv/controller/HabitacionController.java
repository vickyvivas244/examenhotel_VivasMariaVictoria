package ar.edu.unju.escmi.pv.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.repository.HabitacionRepository;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    // Listar todas las habitaciones (con filtro opcional por estado)
    @GetMapping
    public ResponseEntity<List<Habitacion>> listar(@RequestParam(required = false) Boolean estado) {
        List<Habitacion> habitaciones;
        if (estado == null) {
            habitaciones = habitacionRepository.findAll();
        } else {
            habitaciones = habitacionRepository.findByEstado(estado);
        }
        return ResponseEntity.ok(habitaciones);
    }

    // Crear una nueva habitación
    @PostMapping
    public ResponseEntity<Habitacion> crear(@RequestBody Habitacion habitacion) {
        Habitacion nuevaHabitacion = habitacionRepository.save(habitacion);
        return ResponseEntity.ok(nuevaHabitacion);
    }

 // Eliminar lógicamente una habitación
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        Habitacion habitacion = habitacionRepository.findById(codigo).orElseThrow(() ->
            new RuntimeException("Habitación no encontrada con el código: " + codigo));
        habitacion.setEstado(false); // Cambiar el estado a inactivo
        habitacionRepository.save(habitacion);
        return ResponseEntity.noContent().build();
    }
}