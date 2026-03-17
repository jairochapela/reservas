package cursojava.reservas.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanRegistry.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cursojava.reservas.models.Habitacion;
import cursojava.reservas.repositories.HabitacionRepository;

@Controller
public class HabitacionController {
    
    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones")
    public String listarHabitaciones(
        @RequestParam(required = false) LocalDate desde, 
        @RequestParam(required = false) LocalDate hasta,
        @RequestParam(required = false) String tipo,
        Model modelUI) {

        // List<Habitacion> habitaciones;
        // if (desde != null && hasta != null) {
        //     if (tipo != null && !tipo.isEmpty()) {
        //         habitaciones = habitacionRepository.findLibresWithReservasBetween(desde, hasta, tipo);
        //     } else {
        //         habitaciones = habitacionRepository.findLibresWithReservasBetween(desde, hasta);
        //     }
        // } else {
        //     habitaciones = habitacionRepository.findAll();
        // }
        List<Habitacion> habitaciones;
        Specification<Habitacion> spec = Specification.unrestricted();

        if (tipo != null && !tipo.isEmpty()) {
            spec = Specification.where(hasTipo(tipo)).and(spec); 
        }

        if (desde != null && hasta != null) {
            spec = Specification.where(betweenDates(desde, hasta)).and(spec);
        }

        habitaciones = habitacionRepository.findAll(spec);




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
        modelUI.addAttribute("tipos", habitacionRepository.findAllTipos());
        modelUI.addAttribute("desde", desde);
        modelUI.addAttribute("hasta", hasta);
        modelUI.addAttribute("tipo", tipo);

        return "habitaciones";
    }

    private Specification<Habitacion> betweenDates(LocalDate desde, LocalDate hasta) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.or(
                criteriaBuilder.and(
                    criteriaBuilder.greaterThan(root.join("reservas").get("fechaEntrada"), hasta),
                    criteriaBuilder.lessThan(root.join("reservas").get("fechaSalida"), desde)
                ),
                criteriaBuilder.isNull(root.join("reservas").get("fechaEntrada"))
            );
        };
    }

    private static Specification<Habitacion> hasTipo(String tipo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipo"), tipo);
    }
}
