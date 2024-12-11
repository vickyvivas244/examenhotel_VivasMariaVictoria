package ar.edu.unju.escmi.pv.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String guardarReserva(@ModelAttribute Reserva reserva, RedirectAttributes redirectAttributes) {
        try {
            if (reserva.getFechaReserva().isBefore(LocalDate.now())) {
                throw new RuntimeException("La fecha de reserva no puede ser en el pasado.");
            }
            List<Reserva> reservas = reservaService.listar();
            for (Reserva reserva1 : reservas) {

                if (reserva1.getFechaReserva().equals(reserva.getFechaReserva())
                        && reserva1.getHabitacion().getCodigo().equals(reserva.getHabitacion().getCodigo())) {
                    throw new RuntimeException("La fecha de reserva ya existe o la habitacion esta reservada.");
                }
            }
            reservaService.guardar(reserva);
            redirectAttributes.addFlashAttribute("mensajeExito", "Reserva guardada exitosamente.");
            return "redirect:/reservas/lista";

        } catch (RuntimeException e) {
            // Mensaje de error en caso de excepción
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());

            return "redirect:/reservas/form";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return "redirect:/reservas/lista";
    }
}
