package cursojava.reservas.models;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {
    
    @Id
    private UUID localizador;

    @Nonnull
    private String nombreCliente;

    @Nonnull
    private LocalDate fechaEntrada;

    @Nonnull
    private LocalDate fechaSalida;

    @ManyToOne(fetch = FetchType.EAGER)
    @Nonnull
    private Habitacion habitacion;

    public Reserva() {
    }

    public Reserva(String nombreCliente, Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.nombreCliente = nombreCliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }


    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }


    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public UUID getLocalizador() {
        return localizador;
    }

    public void setLocalizador(UUID localizador) {
        this.localizador = localizador;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
}
