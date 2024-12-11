package ar.edu.unju.escmi.pv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ar.edu.unju.escmi.pv.model.Pasajero;
import ar.edu.unju.escmi.pv.repository.PasajeroRepository;
import org.springframework.ui.Model;  


import jakarta.validation.Valid;

@Controller
@RequestMapping("/pasajeros")
public class PasajeroController {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @GetMapping("/lista")
    public String listarPasajeros(Model model) {
        model.addAttribute("pasajeros", pasajeroRepository.findAll());
        return "listaDePasajeros";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("pasajero", new Pasajero());
        return "formPasajero";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid@ModelAttribute Pasajero pasajero) {
        pasajeroRepository.save(pasajero);
        return "redirect:/pasajeros/lista";
    }

    @GetMapping("/eliminar/{dni}")
    public String eliminar(@PathVariable String dni) {
        pasajeroRepository.deleteById(dni);
        return "redirect:/pasajeros/lista";
    }
}
