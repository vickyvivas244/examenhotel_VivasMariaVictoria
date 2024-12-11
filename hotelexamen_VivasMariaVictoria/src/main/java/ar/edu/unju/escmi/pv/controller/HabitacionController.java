package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ar.edu.unju.escmi.pv.model.Habitacion;
import ar.edu.unju.escmi.pv.repository.HabitacionRepository;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("habitacion", new Habitacion()); // Objeto vacío para el formulario
        return "formHabitacion"; // Ruta de la plantilla
    }

    // Listar todas las habitaciones (vista)
    @GetMapping
    public String listar(@RequestParam(required = false) Boolean estado, Model model) {
        model.addAttribute("habitaciones", estado == null ? habitacionRepository.findAll() : habitacionRepository.findByEstado(estado));
        return "listaDeHabitaciones"; // Ruta 
    }

    // Mostrar formulario para crear una habitación
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        return "formHabitacion"; // Ruta
    }

    // Crear una habitación
    @PostMapping
    public String crear(@Valid @ModelAttribute Habitacion habitacion, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formHabitacion";
        }
        habitacionRepository.save(habitacion);
        return "redirect:/listaDeHabitaciones"; // Redirige a la lista
    }

    // Eliminar lógicamente una habitación
    @PostMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable String codigo) {
        Habitacion habitacion = habitacionRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Habitación no encontrada con el código: " + codigo));
        habitacion.setEstado(false); // Cambiar el estado a inactivo
        habitacionRepository.save(habitacion);
        return "redirect:/habitaciones";
    }
}
