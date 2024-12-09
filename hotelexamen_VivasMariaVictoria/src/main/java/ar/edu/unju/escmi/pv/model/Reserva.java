package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo; // Clave primaria
    private LocalDate fechaReserva;

    @ManyToOne
    @JoinColumn(name = "pasajero_dni")
    private Pasajero pasajero; 

    @ManyToOne
    @JoinColumn(name = "habitacion_codigo")
    private Habitacion habitacion; 
}
