package cursojava.reservas.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cursojava.reservas.models.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer>, JpaSpecificationExecutor<Habitacion> {
    // Automaticamente se heredan métodos como findAll(), findById(), save(), deleteById(), etc.

    @Query("SELECT h FROM Habitacion h LEFT JOIN Reserva r ON h.numero = r.habitacion.numero WHERE r.fechaEntrada <= :hasta AND r.fechaSalida >= :desde")
    List<Habitacion> findByRangoFechas(LocalDate desde, LocalDate hasta);

    @Query("SELECT h FROM Habitacion h LEFT JOIN Reserva s ON h.numero = s.habitacion.numero "
        + "WHERE s.fechaEntrada is NULL OR s.fechaEntrada > :hasta AND s.fechaSalida < :desde")
    List<Habitacion> findLibresWithReservasBetween(LocalDate desde, LocalDate hasta);

    @Query("SELECT h FROM Habitacion h LEFT JOIN Reserva s ON h.numero = s.habitacion.numero "
        + "WHERE (s.fechaEntrada is NULL OR s.fechaEntrada > :hasta AND s.fechaSalida < :desde) "
        + "AND h.tipo = :tipo")
    List<Habitacion> findLibresWithReservasBetween(LocalDate desde, LocalDate hasta, String tipo);

    @Query("SELECT DISTINCT h.tipo FROM Habitacion h")
    List<String> findAllTipos();
}
