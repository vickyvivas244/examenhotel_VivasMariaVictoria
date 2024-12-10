package ar.edu.unju.escmi.pv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Pasajero {
    @Id
    @NotBlank(message="Debe ingresar DNI del Pasajero")
	@Size(min=7, max=9,message="longitud del DNI no valida, de 7-9 digitos")
	@Pattern(regexp="[0-9]*",message="Solo se debe ingresar Numeros")
    private String dni; // Clave primaria
	@NotBlank(message="Debe ingresar Apellido del Pasajero")
	@Size(min=3, max=20,message="El Apellido debe contener como minimo 3 Caracteres como minimo y 20 como maximo")
	@Pattern(regexp="[a-z A-Z]*",message="Solo se debe ingresar Letras")
    private String apellido;
    @NotBlank(message="Debe ingresar Nombre del Pasajero")
	@Size(min=3, max=20,message="El nombre debe contener como minimo 3 Caracteres como minimo y 20 como maximo")
	@Pattern(regexp = "^[a-zA-Záéíóú ]*$", message = "Solo se permiten letras y espacios")
    private String nombre;
    @NotNull	
    private String fechaNacimiento;
    @NotBlank(message="Debe ingresar Ciudad de Procedencia del Pasajero")
	@Size(min=8, max=15,message="longitud de ciudad de procedencia no valida")
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

