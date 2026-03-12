package cursojava.reservas.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cursojava.reservas.models.Habitacion;
import cursojava.reservas.models.Reserva;
import cursojava.reservas.repositories.HabitacionRepository;
import cursojava.reservas.repositories.ReservaRepository;
import cursojava.reservas.services.ReservaService;

@Controller
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;
    
    @GetMapping("/reservas")
    public String listarReservas(Model modelUI) {
        List<Reserva> reservas = reservaRepository.findAll();
        modelUI.addAttribute("reservas", reservas);
        return "reservas";
    }

    @GetMapping("/reservas/nueva")
    public String mostrarFormularioNuevaReserva(Model modelUI) {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        modelUI.addAttribute("habitaciones", habitaciones);
        return "nuevareserva";
    }

    @PostMapping("/reservas/nueva")
    public String crearReserva(Reserva reserva, Model modelUI, ReservaService reservaService) {
        reserva.setLocalizador(UUID.randomUUID());

        // Validar fechas
        if (!reservaService.fechasValidas(reserva)) {
            modelUI.addAttribute("reserva", reserva);
            modelUI.addAttribute("habitaciones", habitacionRepository.findAll());
            modelUI.addAttribute("error", "Las fechas de la reserva no son válidas.");
            return "editarreserva";
        }        

        // Validar los datos de la reserva y comprobar disponibilidad de la habitación
        List<Reserva> reservasExistentes = reservaRepository.findByHabitacionNumero(reserva.getHabitacion().getNumero());
        if (!reservaService.estaDisponible(reservasExistentes, reserva)) {
            modelUI.addAttribute("reserva", reserva);
            modelUI.addAttribute("habitaciones", habitacionRepository.findAll());
            modelUI.addAttribute("error", "La habitación no está disponible en las fechas seleccionadas.");
            return "editarreserva";
        }

        reservaRepository.save(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/{localizador}")
    public String mostrarDetalleReserva(@PathVariable UUID localizador, Model modelUI) {
        Reserva reserva = reservaRepository.findById(localizador).orElse(null);
        if (reserva == null) {
            return "redirect:/reservas"; // TODO: mejor mostrar un mensaje de error o una página de "Reserva no encontrada"
        }
        modelUI.addAttribute("reserva", reserva);
        return "detallereserva";
    }

    @PostMapping("/reservas/{localizador}/eliminar")
    public String eliminarReserva(@PathVariable UUID localizador) {
        reservaRepository.deleteById(localizador);
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/{localizador}/editar")
    public String mostrarFormularioEditarReserva(@PathVariable UUID localizador, Model modelUI) {
        Reserva reserva = reservaRepository.findById(localizador).orElse(null);
        if (reserva == null) {
            return "redirect:/reservas"; // TODO: mejor mostrar un mensaje de error o una página de "Reserva no encontrada"
        }
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        modelUI.addAttribute("reserva", reserva);
        modelUI.addAttribute("habitaciones", habitaciones);
        return "editarreserva";
    }

    @PostMapping("/reservas/{localizador}")
    public String actualizarReserva(@PathVariable UUID localizador, Reserva reservaActualizada, ReservaService reservaService, Model modelUI) {
        reservaActualizada.setLocalizador(localizador);

        // Validar fechas
        if (!reservaService.fechasValidas(reservaActualizada)) {
            modelUI.addAttribute("reserva", reservaActualizada);
            modelUI.addAttribute("habitaciones", habitacionRepository.findAll());
            modelUI.addAttribute("error", "Las fechas de la reserva no son válidas.");
            return "editarreserva";
        }        

        // Validar los datos de la reserva y comprobar disponibilidad de la habitación
        List<Reserva> reservasExistentes = reservaRepository.findByHabitacionNumero(reservaActualizada.getHabitacion().getNumero());
        if (!reservaService.estaDisponible(reservasExistentes, reservaActualizada)) {
            modelUI.addAttribute("reserva", reservaActualizada);
            modelUI.addAttribute("habitaciones", habitacionRepository.findAll());
            modelUI.addAttribute("error", "La habitación no está disponible en las fechas seleccionadas.");
            return "editarreserva";
        }

        reservaRepository.save(reservaActualizada);
        return "redirect:/reservas/" + localizador;
    }
}