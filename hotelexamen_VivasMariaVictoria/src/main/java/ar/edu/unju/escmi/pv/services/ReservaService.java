package ar.edu.unju.escmi.pv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.escmi.pv.model.Reserva;
import ar.edu.unju.escmi.pv.repository.ReservaRepository;

@Service
public class ReservaService {
  @Autowired
  private ReservaRepository reservaRepository;

  public List<Reserva> listar() {
    List<Reserva> reservas = reservaRepository.findAll();
    return reservas;
  }

  public void guardar(Reserva reserva) {
    reservaRepository.save(reserva);
  }

  public void eliminar(Long id) {
    reservaRepository.deleteById(id);
  }

}
