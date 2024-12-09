package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Habitacion {
    @Id
    private String codigo; // Clave primaria
    @Enumerated(EnumType.STRING)
    private Tipo tipo; 
    private String servicios;
    private String descripcion;
    private boolean estado = true; // Disponible o no

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas; 

    public enum Tipo {
        SIMPLE, DOBLE, SUITE
    }
}
