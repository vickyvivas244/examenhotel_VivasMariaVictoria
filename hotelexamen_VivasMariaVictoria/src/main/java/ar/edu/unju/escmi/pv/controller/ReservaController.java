package ar.edu.unju.escmi.pv.controller;

import org.springframework.web.bind.annotation.*;
import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.model.Pasajero;
import ar.edu.unju.escmi.pv.model.Reserva;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private List<Reserva> reservas = new ArrayList<>();
    private List<Pasajero> pasajeros = new ArrayList<>();
    private List<Habitacion> habitaciones = new ArrayList<>();

    // Crear una nueva reserva
    @PostMapping
    public Reserva crear(
            @RequestParam String dni, 
            @RequestParam String codigoHabitacion, 
            @RequestParam String fechaReserva) {

        // Verificar que el pasajero exista
        Pasajero pasajero = pasajeros.stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado con el DNI: " + dni));

        // Verificar que la habitación exista
        Habitacion habitacion = habitaciones.stream()
                .filter(h -> h.getCodigo().equals(codigoHabitacion))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con el código: " + codigoHabitacion));

        // Verificar si la habitación está disponible en la fecha
        LocalDate fecha = LocalDate.parse(fechaReserva);
        boolean estaDisponible = reservas.stream()
                .noneMatch(r -> r.getHabitacion().equals(habitacion) && r.getFechaReserva().equals(fecha));

        if (!estaDisponible) {
            throw new RuntimeException("La habitación ya está reservada en esta fecha.");
        }

        // Crear la reserva
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setPasajero(pasajero);
        nuevaReserva.setHabitacion(habitacion);
        nuevaReserva.setFechaReserva(fecha);

        reservas.add(nuevaReserva);
        return nuevaReserva;
    }

    // Listar todas las reservas
    @GetMapping
    public List<Reserva> listar() {
        return reservas;
    }
}
