package br.unicamp.MovItUnicamp.service;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.corrida.CorridaRepository;
import br.unicamp.MovItUnicamp.model.reserva.Reserva;
import br.unicamp.MovItUnicamp.model.reserva.ReservaRepository;
import br.unicamp.MovItUnicamp.model.service.CorridaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CorridaServiceTest {

    @Mock
    private CorridaRepository corridaRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private CorridaService service;

    private Reserva reservaStub;

    @BeforeEach
    void setup() {
        reservaStub = new Reserva();
    }

    @Test
    void deveIniciarCorrida_comDistanciaPositiva() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reservaStub));
        when(corridaRepository.save(any(Corrida.class))).thenAnswer(inv -> inv.getArgument(0));
        Corrida result = service.iniciarCorrida(1, 5.0);
        assertSame(reservaStub, result.getReserva());
        assertEquals(5.0, result.getDistanciaPercorrida());
    }

    @Test
    void deveIniciarCorrida_comDistanciaZero() {
        when(reservaRepository.findById(2)).thenReturn(Optional.of(reservaStub));
        when(corridaRepository.save(any(Corrida.class))).thenAnswer(inv -> inv.getArgument(0));
        Corrida result = service.iniciarCorrida(2, 0.0);
        assertEquals(0.0, result.getDistanciaPercorrida());
    }

    @Test
    void deveIniciarCorrida_comDistanciaNegativa() {
        when(reservaRepository.findById(3)).thenReturn(Optional.of(reservaStub));
        when(corridaRepository.save(any(Corrida.class))).thenAnswer(inv -> inv.getArgument(0));
        Corrida result = service.iniciarCorrida(3, -0.1);
        assertEquals(-0.1, result.getDistanciaPercorrida());
    }

    @Test
    void deveEncerrarCorrida_comDistanciaPositiva() {
        Corrida existing = new Corrida(reservaStub, LocalDateTime.now(), null, 0.0);
        when(corridaRepository.findById(10)).thenReturn(Optional.of(existing));
        when(corridaRepository.save(any(Corrida.class))).thenAnswer(inv -> inv.getArgument(0));
        Corrida result = service.encerrarCorrida(10, 12.5);
        assertNotNull(result.getDataFim());
        assertEquals(12.5, result.getDistanciaPercorrida());
    }

    @Test
    void deveEncerrarCorrida_comDistanciaZero() {
        Corrida existing = new Corrida(reservaStub, LocalDateTime.now(), null, 1.0);
        when(corridaRepository.findById(11)).thenReturn(Optional.of(existing));
        when(corridaRepository.save(any(Corrida.class))).thenAnswer(inv -> inv.getArgument(0));
        Corrida result = service.encerrarCorrida(11, 0.0);
        assertEquals(0.0, result.getDistanciaPercorrida());
    }

    @Test
    void deveEncerrarCorrida_comDistanciaNegativa() {
        Corrida existing = new Corrida(reservaStub, LocalDateTime.now(), null, 2.0);
        when(corridaRepository.findById(12)).thenReturn(Optional.of(existing));
        when(corridaRepository.save(any(Corrida.class))).thenAnswer(inv -> inv.getArgument(0));
        Corrida result = service.encerrarCorrida(12, -5.0);
        assertEquals(-5.0, result.getDistanciaPercorrida());
    }

    @Test
    void listarCorridas_deveRetornarTudo() {
        Corrida c1 = new Corrida(reservaStub, LocalDateTime.now(), null, 1.0);
        Corrida c2 = new Corrida(reservaStub, LocalDateTime.now(), null, 2.0);
        when(corridaRepository.findAll()).thenReturn(Arrays.asList(c1, c2));
        List<Corrida> list = service.listarCorridas();
        assertEquals(2, list.size());
    }

    @Test
    void excluirCorrida_deveChamarDelete() {
        doNothing().when(corridaRepository).deleteById(99);
        service.excluirCorrida(99);
        verify(corridaRepository, times(1)).deleteById(99);
    }
}
