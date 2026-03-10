package cursojava.reservas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.reservas.models.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    // Automaticamente se heredan métodos como findAll(), findById(), save(), deleteById(), etc.
}
