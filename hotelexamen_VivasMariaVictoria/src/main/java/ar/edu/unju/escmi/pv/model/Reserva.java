package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo; // Clave primaria
    @NotNull(message = "La fecha de la reserva no puede ser nula")
    private LocalDate fechaReserva;

    @ManyToOne
    private Pasajero pasajero;

    @ManyToOne
    private Habitacion habitacion;
}
