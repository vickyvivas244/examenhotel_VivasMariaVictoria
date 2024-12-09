package ar.edu.unju.escmi.pv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.model.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Método para encontrar reservas por habitación y fecha
    List<Reserva> findByHabitacionAndFechaReserva(Habitacion habitacion, LocalDate fechaReserva);
}
