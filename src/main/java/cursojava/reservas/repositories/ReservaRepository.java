package cursojava.reservas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.reservas.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    // Automaticamente se heredan métodos como findAll(), findById(), save(), deleteById(), etc.
}
