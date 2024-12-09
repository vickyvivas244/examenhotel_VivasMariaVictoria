package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.model.Pasajero;
import ar.edu.unju.escmi.pv.model.Reserva;
import ar.edu.unju.escmi.pv.repository.HabitacionRepository;
import ar.edu.unju.escmi.pv.repository.PasajeroRepository;
import ar.edu.unju.escmi.pv.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<Reserva> crear(
            @RequestParam String dni, 
            @RequestParam String codigoHabitacion, 
            @RequestParam String fechaReserva) {

        // Verificar que el pasajero exista
        Pasajero pasajero = pasajeroRepository.findById(dni).orElseThrow(() -> 
            new RuntimeException("Pasajero no encontrado con el DNI: " + dni));

        // Verificar que la habitación exista
        Habitacion habitacion = habitacionRepository.findById(codigoHabitacion).orElseThrow(() -> 
            new RuntimeException("Habitación no encontrada con el código: " + codigoHabitacion));

        // Verificar si la habitación está disponible en la fecha
        LocalDate fecha = LocalDate.parse(fechaReserva);
        boolean estaDisponible = reservaRepository.findAll()
                .stream()
                .noneMatch(r -> r.getHabitacion().equals(habitacion) && r.getFechaReserva().equals(fecha));

        if (!estaDisponible) {
            throw new RuntimeException("La habitación ya está reservada en esta fecha.");
        }

        // Crear la reserva
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setPasajero(pasajero);
        nuevaReserva.setHabitacion(habitacion);
        nuevaReserva.setFechaReserva(fecha);

        // Guardar la reserva en la base de datos
        Reserva reservaGuardada = reservaRepository.save(nuevaReserva);
        return ResponseEntity.ok(reservaGuardada);
    }

    // Listar todas las reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> listar() {
        return ResponseEntity.ok(reservaRepository.findAll());
    }
}
