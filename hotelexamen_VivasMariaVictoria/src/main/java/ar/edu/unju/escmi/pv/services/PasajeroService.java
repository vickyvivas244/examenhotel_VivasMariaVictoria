package ar.edu.unju.escmi.pv.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.escmi.pv.model.Pasajero;
import ar.edu.unju.escmi.pv.repository.PasajeroRepository;

@Service
public class PasajeroService {
  @Autowired
  private PasajeroRepository pasajeroRepository;

  public void guardarPasajero(Pasajero pasajero) {
    pasajeroRepository.save(pasajero);
  }

  public void eliminarPasajero(String dni) {
    Pasajero pasajero = pasajeroRepository.findById(dni).orElse(null);
    if (pasajero != null) {
      pasajero.setEstado(false);
      pasajeroRepository.save(pasajero);
    }
  }

  public List<Pasajero> listar() {
    List<Pasajero> pasajeros = pasajeroRepository.findAll();
    List<Pasajero> pasajerosActivos = new ArrayList<>();
    for (Pasajero pasajero : pasajeros) {
      if (pasajero.isEstado()) {
        pasajerosActivos.add(pasajero);
      }
    }
    return pasajerosActivos;
  }
}
