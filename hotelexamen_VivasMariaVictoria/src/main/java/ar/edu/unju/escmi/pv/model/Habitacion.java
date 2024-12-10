package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Habitacion {
    @Id
    @Size (min=5, max=10, message="El codigo debe contener como mínimo 5 caracteres y como máximo 10 caracteres")
	@Pattern (regexp= "[a-z A-Z]*+[0-9]*", message="Debe ingresar únicamente letras y numeros")
    private String codigo; // Clave primaria
    @Enumerated(EnumType.STRING)
    private Tipo tipo; 
	@NotBlank (message="Debe ingresar los servicios de la habitacion")
    private String servicios;
	@NotBlank (message="Debe ingresar descripcion de la habitacion")
    private String descripcion;
    private boolean estado = true; // Disponible o no

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas; 

    public enum Tipo {
        SIMPLE, DOBLE, SUITE
    }
}
