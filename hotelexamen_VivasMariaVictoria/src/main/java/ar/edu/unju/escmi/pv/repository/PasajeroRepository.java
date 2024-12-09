package ar.edu.unju.escmi.pv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.escmi.pv.model.Pasajero;

import java.util.List;

public interface PasajeroRepository extends JpaRepository<Pasajero, String> {
    List<Pasajero> findByEstado(Boolean estado);
}
