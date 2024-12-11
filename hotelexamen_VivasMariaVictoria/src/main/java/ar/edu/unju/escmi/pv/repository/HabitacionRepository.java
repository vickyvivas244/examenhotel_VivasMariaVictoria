package ar.edu.unju.escmi.pv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.escmi.pv.model.Habitacion;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    // Método personalizado para filtrar habitaciones por estado
    List<Habitacion> findByEstado(boolean estado);
}
