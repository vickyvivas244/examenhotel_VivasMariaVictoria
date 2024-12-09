package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Pasajero {
    @Id
    private String dni; // Clave primaria
    private String apellido;
    private String nombre;
    private String fechaNacimiento; 
    private String ciudadProcedencia;
    @Enumerated(EnumType.STRING)
    private Nacionalidad nacionalidad; 
    private boolean estado = true; 

    @OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public enum Nacionalidad {
        ARGENTINA, BRASILEÑA, CHILENA, URUGUAYA
    }
}

