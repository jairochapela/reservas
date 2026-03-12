package cursojava.reservas.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cursojava.reservas.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    // Automaticamente se heredan métodos como findAll(), findById(), save(), deleteById(), etc.

    @Query("SELECT r FROM Reserva r WHERE r.habitacion.numero = :numeroHabitacion")
    List<Reserva> findByHabitacionNumero(int numeroHabitacion);
}
