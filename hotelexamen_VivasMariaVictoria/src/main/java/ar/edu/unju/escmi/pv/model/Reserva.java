package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reserva {
    @Id
    @Size (min=5, max=10, message="El codigo debe contener como mínimo 5 caracteres y como máximo 10 caracteres")
   	@Pattern (regexp= "[a-z A-Z]*+[0-9]*", message="Debe ingresar únicamente letras y numeros")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo; // Clave primaria
    @NotNull(message = "La fecha de la reserva no puede ser nula")
    private LocalDate fechaReserva;

    @ManyToOne
    @JoinColumn(name = "pasajero_dni")
    private Pasajero pasajero; 

    @ManyToOne
    @JoinColumn(name = "habitacion_codigo")
    private Habitacion habitacion; 
}
