package cursojava.reservas.models;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Habitacion {
    
    @Id
    private int numero;

    @Nonnull
    private String tipo;

    @OneToMany(mappedBy = "habitacion")
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
