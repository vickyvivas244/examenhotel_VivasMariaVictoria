package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.springframework.stereotype.Component;

@Data
@Entity
@Component
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo; // Clave primaria
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @NotBlank(message = "Debe ingresar los servicios de la habitacion")
    private String servicios;
    @NotBlank(message = "Debe ingresar descripcion de la habitacion")
    private String descripcion;
    private boolean estado = true; // Disponible o no

    public enum Tipo {
        SIMPLE, DOBLE, SUITE
    }
}

