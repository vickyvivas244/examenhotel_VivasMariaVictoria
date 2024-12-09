package ar.edu.unju.escmi.pv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.escmi.pv.model.Habitacion;

import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, String> {
    // Método personalizado para filtrar habitaciones por estado
    List<Habitacion> findByEstado(boolean estado);
}
