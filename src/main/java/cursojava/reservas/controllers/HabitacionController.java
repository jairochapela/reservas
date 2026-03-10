package cursojava.reservas.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cursojava.reservas.models.Habitacion;
import cursojava.reservas.repositories.HabitacionRepository;

@Controller
public class HabitacionController {
    
    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/habitaciones")
    public String listarHabitaciones(Model modelUI) {

        List<Habitacion> habitaciones = habitacionRepository.findAll();

        // Organizar las habitaciones por piso
        List<List<Habitacion>> hotel = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            int numero = h.getNumero();
            int piso = numero / 100;
            while (hotel.size() < piso) {
                hotel.add(new ArrayList<>());
            }
            hotel.get(piso-1).add(h);
        }

        modelUI.addAttribute("hotel", hotel);

        return "habitaciones";
    }
}
