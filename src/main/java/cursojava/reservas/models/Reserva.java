package cursojava.reservas.models;

import java.time.LocalDate;

public class Reserva {
    
    private String nombreCliente;
    private int numeroHabitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    public Reserva() {
    }

    public Reserva(String nombreCliente, int numeroHabitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.nombreCliente = nombreCliente;
        this.numeroHabitacion = numeroHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
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

    
}
