package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.services.HabitacionService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("habitacion", new Habitacion()); // Objeto vacío para el formulario
        return "formHabitacion"; // Ruta de la plantilla
    }

    // Listar todas las habitaciones (vista)
    @GetMapping()
    public String listar(@RequestParam(required = false) Boolean estado, Model model) {
        model.addAttribute("habitaciones",
                estado == null ? habitacionService.listar() : habitacionService.listarEstado(estado));
        return "listaDeHabitaciones"; // Ruta
    }

    // Mostrar formulario para crear una habitación
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        return "formHabitacion"; // Ruta
    }

    // Crear una habitación
    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute Habitacion habitacion, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formHabitacion";
        }
        habitacionService.guardarHabitacion(habitacion);
        return "redirect:/habitaciones"; // Redirige a la lista
    }

    // Eliminar lógicamente una habitación
    @PostMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable Long codigo) {
        habitacionService.eliminarHabitacion(codigo);
        return "redirect:/habitaciones";
    }
}
