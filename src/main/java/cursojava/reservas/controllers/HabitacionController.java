package cursojava.reservas.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cursojava.reservas.models.Habitacion;
import cursojava.reservas.models.Reserva;

@Controller
public class HabitacionController {
    
    @GetMapping("/habitaciones")
    public String listarHabitaciones(Model modelUI) {
        List<List<Habitacion>> hotel = List.of(
            List.of(
                new Habitacion(101, "Simple"), 
                new Habitacion(102, "Simple"), 
                new Habitacion(103, "Doble"), 
                new Habitacion(104, "Doble"), 
                new Habitacion(105, "Doble")
            ),
            List.of(
                new Habitacion(201, "Simple"), 
                new Habitacion(202, "Simple"), 
                new Habitacion(203, "Doble"), 
                new Habitacion(204, "Doble"), 
                new Habitacion(205, "Doble")
            ),
            List.of(
                new Habitacion(301, "Simple"), 
                new Habitacion(302, "Simple", List.of(
                    new Reserva("Pepito Pérez", 302, LocalDate.of(2026, 3, 8), LocalDate.of(2026, 3, 15))
                )),
                new Habitacion(303, "Doble"),
                new Habitacion(304, "Doble"), 
                new Habitacion(305, "Doble")
            ),
            List.of(
                new Habitacion(401, "Simple"), 
                new Habitacion(402, "Simple"),
                new Habitacion(403, "Doble"),
                new Habitacion(404, "Doble"),
                new Habitacion(405, "Doble")
            ),
            List.of(
                new Habitacion(501, "Simple"), 
                new Habitacion(502, "Simple"), 
                new Habitacion(503, "Simple"), 
                new Habitacion(504, "Doble"), 
                new Habitacion(505, "Suite")
            )
        );

        modelUI.addAttribute("hotel", hotel);

        return "habitaciones";
    }
}
