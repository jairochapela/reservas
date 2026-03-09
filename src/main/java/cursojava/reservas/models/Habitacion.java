package cursojava.reservas.models;

import java.util.List;

public class Habitacion {
    
    private int numero;

    private String tipo;

    private List<Reserva> reservas;

    public Habitacion() {
    }

    public Habitacion(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.reservas = List.of(); // Lista vacía por defecto
    }

    public Habitacion(int numero, String tipo, List<Reserva> reservas) {
        this.numero = numero;
        this.tipo = tipo;
        this.reservas = reservas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    
}
