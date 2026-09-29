package edu.uees.testing.service;

import edu.uees.testing.availability.DisponibilidadClient;
import edu.uees.testing.domain.EstadoReserva;
import edu.uees.testing.domain.Reserva;
import edu.uees.testing.notification.Notificador;
import edu.uees.testing.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private DisponibilidadClient disponibilidad;

    @Mock
    private ReservaRepository repository;

    @Mock
    private Notificador notificador;

    private ReservaService servicio;

    @BeforeEach
    void setUp() {
        servicio = new ReservaService(disponibilidad, repository, notificador);
    }

    @Test
    void puedeCancelarConCincoHoras() {
        assertTrue(servicio.puedeCancelar(5));
    }

    @Test
    void puedeCancelarEnElLimiteDeDosHoras() {
        assertTrue(servicio.puedeCancelar(2));
    }

    @Test
    void noPuedeCancelarConUnaHora() {
        assertFalse(servicio.puedeCancelar(1));
    }

    @Test
    void calcularTotalNormalMantieneElTotalBase() {
        assertEquals(100.0, servicio.calcularTotal("NORMAL", 100.0));
    }

    @Test
    void calcularTotalVipAplicaQuincePorCientoDeDescuento() {
        assertEquals(85.0, servicio.calcularTotal("VIP", 100.0), 0.001);
    }

    @Test
    void calcularTotalEstudianteAplicaDiezPorCientoDeDescuento() {
        assertEquals(90.0, servicio.calcularTotal("ESTUDIANTE", 100.0), 0.001);
    }

    @Test
    void calcularTotalAceptaTipoVipSinImportarMayusculas() {
        assertEquals(85.0, servicio.calcularTotal("vip", 100.0), 0.001);
    }

    @Test
    void calcularTotalNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularTotal("NORMAL", -1));
    }

    @Test
    void confirmarReservaDisponibleCambiaEstadoYGuardaYNotifica() {
        Reserva reserva = new Reserva("R-001", "NORMAL");
        when(disponibilidad.estaDisponible(reserva)).thenReturn(true);

        servicio.confirmar(reserva);

        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        verify(repository).guardar(reserva);
        verify(notificador).enviarConfirmacion(reserva);
    }

    @Test
    void confirmarReservaNoDisponibleLanzaExcepcionYNoGuardaNiNotifica() {
        Reserva reserva = new Reserva("R-002", "NORMAL");
        when(disponibilidad.estaDisponible(reserva)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> servicio.confirmar(reserva));

        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        verify(repository, never()).guardar(any());
        verify(notificador, never()).enviarConfirmacion(any());
    }

    @Test
    void confirmarReservaNulaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.confirmar(null));
        verifyNoInteractions(disponibilidad, repository, notificador);
    }

    @Test
    void confirmarReservaDisponibleConsultaDisponibilidadAntesDePersistir() {
        Reserva reserva = new Reserva("R-003", "VIP");
        when(disponibilidad.estaDisponible(reserva)).thenReturn(true);

        servicio.confirmar(reserva);

        var inOrder = inOrder(disponibilidad, repository, notificador);
        inOrder.verify(disponibilidad).estaDisponible(reserva);
        inOrder.verify(repository).guardar(reserva);
        inOrder.verify(notificador).enviarConfirmacion(reserva);
    }
}
