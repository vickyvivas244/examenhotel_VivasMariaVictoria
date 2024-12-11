package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*;
import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.model.Reserva;
import ar.edu.unju.escmi.pv.repository.HabitacionRepository;
import ar.edu.unju.escmi.pv.repository.ReservaRepository;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/lista")
    public String listarReservas(Model model) { 
        model.addAttribute("reservas", reservaRepository.findAll());
        return "listaDeReservas"; 
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) { 
        model.addAttribute("reserva", new Reserva());
        return "formReserva"; 
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Reserva reserva) {
        Habitacion habitacion = habitacionRepository.findById(reserva.getHabitacion().getCodigo())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        reserva.setHabitacion(habitacion);
        reservaRepository.save(reserva);
        return "redirect:/reservas/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/reservas/lista";
    }
}
