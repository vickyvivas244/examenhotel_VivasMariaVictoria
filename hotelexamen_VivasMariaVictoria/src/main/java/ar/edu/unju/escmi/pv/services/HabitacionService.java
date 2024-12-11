package ar.edu.unju.escmi.pv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.repository.HabitacionRepository;

@Service
public class HabitacionService {
  @Autowired
  private HabitacionRepository habitacionRepository;

  public void guardarHabitacion(Habitacion habitacion) {
    habitacionRepository.save(habitacion);
  }

  public void eliminarHabitacion(String id) {
    Habitacion habitacion = habitacionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar la habitacion con el id: " + id));
    habitacion.setEstado(false);
    habitacionRepository.save(habitacion);
  }

  public List<Habitacion> listar() {
    return habitacionRepository.findAll();
  }

  public List<Habitacion> listarEstado(boolean estado) {
    return habitacionRepository.findByEstado(estado);
  }

  public Habitacion buscarHabitacion(String codigo) {
    return habitacionRepository.findById(codigo).orElse(null);
  }
}
