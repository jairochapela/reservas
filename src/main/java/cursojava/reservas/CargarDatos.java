package cursojava.reservas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cursojava.reservas.models.Habitacion;
import cursojava.reservas.repositories.HabitacionRepository;

/**
 * Programa de carga de datos.
 * Toma los datos de habitaciones desde un archivo CSV y los guarda en la base de datos al iniciar la aplicación.
 * 
 * Inspirado en:
 * https://spring.io/guides/gs/accessing-data-jpa
 */
@SpringBootApplication
public class CargarDatos {
    


    public static void main(String[] args) {
        SpringApplication.run(CargarDatos.class, args);        
    }

    @Bean
    public CommandLineRunner demo(HabitacionRepository habitacionRepository) {
        return args -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader("data/habitaciones.csv"));
                br.lines()
                    .skip(1) // Omitir la primera línea (encabezados)
                    .forEach(line -> {
                        String[] fields = line.split(",");
                        int numeroHabitacion = Integer.parseInt(fields[0]);
                        String tipo = fields[1];

                        Habitacion habitacion = new Habitacion(numeroHabitacion, tipo);
                        habitacionRepository.save(habitacion);
                    });
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
