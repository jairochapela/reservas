package cursojava.reservas.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cursojava.reservas.models.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    // Automaticamente se heredan métodos como findAll(), findById(), save(), deleteById(), etc.

    @Query("SELECT h FROM Habitacion h LEFT JOIN Reserva r ON h.numero = r.habitacion.numero WHERE r.fechaEntrada <= :hasta AND r.fechaSalida >= :desde")
    List<Habitacion> findByRangoFechas(LocalDate desde, LocalDate hasta);
}
