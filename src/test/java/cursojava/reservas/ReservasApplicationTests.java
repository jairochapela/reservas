package cursojava.reservas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import cursojava.reservas.models.Habitacion;
import cursojava.reservas.models.Reserva;
import cursojava.reservas.services.ReservaService;

class ReservasApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testConUnaReservaLibre() {
		Habitacion h = new Habitacion(101, "Doble");
		List<Reserva> reservas = List.of(new Reserva("A", h, LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 7)));
		Reserva nuevaReserva = new Reserva("B", h, LocalDate.of(2026, 4, 8), LocalDate.of(2026, 4, 10));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertTrue(disponible);
	}

	@Test
	void testConUnaReservaOtraHabitacion() {
		Habitacion h = new Habitacion(101, "Doble");
		Habitacion h2 = new Habitacion(202, "Doble");
		List<Reserva> reservas = List.of(new Reserva("A", h, LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 7)));
		Reserva nuevaReserva = new Reserva("B", h2, LocalDate.of(2026, 4, 5), LocalDate.of(2026, 4, 10));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertTrue(disponible);
	}

	@Test
	void testConUnaReservaOcupada() {
		Habitacion h = new Habitacion(101, "Doble");
		List<Reserva> reservas = List.of(new Reserva("A", h, LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 7)));
		Reserva nuevaReserva = new Reserva("B", h, LocalDate.of(2026, 4, 6), LocalDate.of(2026, 4, 10));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertFalse(disponible);
	}	

	@Test
	void testConUnaReservaOcupadaPosterior() {
		Habitacion h = new Habitacion(101, "Doble");
		List<Reserva> reservas = List.of(new Reserva("A", h, LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 17)));
		Reserva nuevaReserva = new Reserva("B", h, LocalDate.of(2026, 4, 6), LocalDate.of(2026, 4, 9));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertTrue(disponible);
	}		

	@Test
	void testConUnaReservaOcupadaAntesYDespues() {
		Habitacion h = new Habitacion(101, "Doble");
		List<Reserva> reservas = List.of(new Reserva("A", h, LocalDate.of(2026, 4, 8), LocalDate.of(2026, 4, 10)));
		Reserva nuevaReserva = new Reserva("B", h, LocalDate.of(2026, 4, 7), LocalDate.of(2026, 4, 11));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertFalse(disponible);
	}		
	
	@Test
	void testConUnaReservaOcupadaEntre() {
		Habitacion h = new Habitacion(101, "Doble");
		List<Reserva> reservas = List.of(new Reserva("A", h, LocalDate.of(2026, 4, 7), LocalDate.of(2026, 4, 11)));
		Reserva nuevaReserva = new Reserva("B", h, LocalDate.of(2026, 4, 8), LocalDate.of(2026, 4, 10));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertFalse(disponible);
	}	

	@Test
	void testConDosReservaLibreEntre() {
		Habitacion h = new Habitacion(101, "Doble");
		List<Reserva> reservas = List.of(
			new Reserva("A", h, LocalDate.of(2026, 4, 7), LocalDate.of(2026, 4, 10)),
			new Reserva("C", h, LocalDate.of(2026, 4, 13), LocalDate.of(2026, 4, 15))
		);
		Reserva nuevaReserva = new Reserva("B", h, LocalDate.of(2026, 4, 11), LocalDate.of(2026, 4, 12));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertTrue(disponible);
	}	

	@Test
	void testConDosReservaOcupadaEntre() {
		Habitacion h = new Habitacion(101, "Doble");
		List<Reserva> reservas = List.of(
			new Reserva("A", h, LocalDate.of(2026, 4, 7), LocalDate.of(2026, 4, 10)),
			new Reserva("C", h, LocalDate.of(2026, 4, 13), LocalDate.of(2026, 4, 15))
		);
		Reserva nuevaReserva = new Reserva("B", h, LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 14));

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertFalse(disponible);
	}

	@Test
	void testConUnaReservaMismoLocalizador() {
		Habitacion h = new Habitacion(101, "Doble");
		Reserva reservaExistente = new Reserva("A", h, LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 7));
		UUID uuid = UUID.randomUUID();
		reservaExistente.setLocalizador(uuid);
		List<Reserva> reservas = List.of(reservaExistente);
		Reserva nuevaReserva = reservaExistente; // Mismo objeto, mismo localizador

		ReservaService reservaService = new ReservaService();
		boolean disponible = reservaService.estaDisponible(reservas, nuevaReserva);
		assertTrue(disponible);
	}	


	@Test
	void testReservaParaDiaPasado() {
		Habitacion h = new Habitacion(101, "Doble");
		LocalDate hoy = LocalDate.now();
		LocalDate ayer = hoy.minusDays(1);
		LocalDate manana = hoy.plusDays(1);

		Reserva nuevaReserva = new Reserva("B", h, ayer, manana);

		ReservaService reservaService = new ReservaService();
		boolean valida = reservaService.fechasValidas(nuevaReserva);
		assertFalse(valida); // No se permite reservar para fechas pasadas
	}


	@Test
	void testReservaFechasInvertidas() {
		Habitacion h = new Habitacion(101, "Doble");
		LocalDate hoy = LocalDate.now();
		LocalDate ayer = hoy.minusDays(1);
		LocalDate manana = hoy.plusDays(1);

		Reserva nuevaReserva = new Reserva("B", h, manana, ayer);

		ReservaService reservaService = new ReservaService();
		boolean valida = reservaService.fechasValidas(nuevaReserva);
		assertFalse(valida); // No tienen sentido las fechas invertidas, se considera no disponible
	}	


	@Test
	void testReservaFechasCorrectas() {
		Habitacion h = new Habitacion(101, "Doble");
		LocalDate hoy = LocalDate.now();
		LocalDate manana = hoy.plusDays(1);
		LocalDate pasadomanana = hoy.plusDays(2);

		Reserva nuevaReserva = new Reserva("B", h, manana, pasadomanana);

		ReservaService reservaService = new ReservaService();
		boolean valida = reservaService.fechasValidas(nuevaReserva);
		assertTrue(valida); // No tienen sentido las fechas invertidas, se considera no disponible
	}
}
