package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.model.Reserva;
import ar.edu.unju.escmi.pv.services.HabitacionService;
import ar.edu.unju.escmi.pv.services.PasajeroService;
import ar.edu.unju.escmi.pv.services.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private HabitacionService habitacionService;
    @Autowired
    private PasajeroService pasajeroService;

    @GetMapping("/lista")
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaService.listar());
        return "listaDeReservas";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("pasajeros", pasajeroService.listar());
        model.addAttribute("habitaciones", habitacionService.listar());
        return "formReserva";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Reserva reserva) {
        Habitacion habitacion = habitacionService.buscarHabitacion(reserva.getHabitacion().getCodigo());
        reserva.setHabitacion(habitacion);
        reservaService.guardar(reserva);
        return "redirect:/reservas/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return "redirect:/reservas/lista";
    }
}