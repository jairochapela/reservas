package cursojava.reservas.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import cursojava.reservas.models.Reserva;

@Service
public class ReservaService {
    
    /**
     * Comprueba si una nueva reserva es compatible con las reservas existentes, es decir, 
     * que no se solapan en fechas para la misma habitación.
     * @param reservasExistentes Lista de reservas existentes
     * @param nuevaReserva La nueva reserva a comprobar
     * @return true si la nueva reserva no se solapa con ninguna existente, false en caso contrario
     */
    public boolean estaDisponible(List<Reserva> reservasExistentes, Reserva nuevaReserva) {
        for (Reserva r : reservasExistentes) {
            if (r.getHabitacion().getNumero() != nuevaReserva.getHabitacion().getNumero()) {
                continue; // Si no es la misma habitación, no hay conflicto
            }

            if (nuevaReserva.getLocalizador() != null &&  r.getLocalizador().equals(nuevaReserva.getLocalizador())) {
                continue; // Si es la misma reserva (mismo localizador), no hay conflicto
            }

            // Comprobar si las fechas se solapan
            if (nuevaReserva.getFechaEntrada().isBefore(r.getFechaSalida()) && nuevaReserva.getFechaSalida().isAfter(r.getFechaEntrada())) {
                return false;
            }
        }
        return true;
    }


    /**
     * Valida que las fechas de entrada y salida de una reserva sean correctas, es decir,
     * que la fecha de entrada sea anterior a la fecha de salida y que ambas sean posteriores
     * a la fecha actual.
     * @param reserva La reserva a validar
     * @return true si las fechas son válidas, false en caso contrario
     */
    public boolean fechasValidas(Reserva reserva) {
        return reserva.getFechaEntrada().isBefore(reserva.getFechaSalida()) 
            && reserva.getFechaEntrada().isAfter(LocalDate.now());
    }
}
