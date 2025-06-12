package br.unicamp.MovItUnicamp.service;

import br.unicamp.MovItUnicamp.model.reserva.Reserva;
import br.unicamp.MovItUnicamp.model.reserva.ReservaRepository;
import br.unicamp.MovItUnicamp.model.service.ReservaService;
import br.unicamp.MovItUnicamp.model.usuario.DadosCadastroUsuario;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import br.unicamp.MovItUnicamp.model.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Usuario usuarioMock;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        usuarioMock = new Usuario(
                new DadosCadastroUsuario(
                        "Joao",
                        "joao@mail.com",
                        "123456",
                        "01/01/2000",
                        "+55 (19) 91234-5678",
                        "Senha@123"
                )
        );
        reservaMock = new Reserva(usuarioMock, 1);
    }

    @Test
    void criarReserva_validos_retornaReserva() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioMock));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        Reserva r = reservaService.criarReserva(1, 1);

        assertNotNull(r);
        assertEquals(usuarioMock, r.getUsuario());
        assertEquals(1, r.getBicicletaId());
        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void criarReserva_usuarioInexistente_salvaComUsuarioNull() {
        when(usuarioRepository.findById(0)).thenReturn(Optional.empty());
        // simulamos o save para não retornar null
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        Reserva r = reservaService.criarReserva(0, 1);

        assertNotNull(r);
        ArgumentCaptor<Reserva> captor = ArgumentCaptor.forClass(Reserva.class);
        verify(reservaRepository).save(captor.capture());
        assertNull(captor.getValue().getUsuario(), "esperava usuário null na reserva");
    }

    @Test
    void buscarPorId_existente_retornaReserva() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reservaMock));

        Reserva r = reservaService.buscarPorId(1);

        assertNotNull(r);
        assertEquals(reservaMock, r);
    }

    @Test
    void buscarPorId_inexistente_retornaNull() {
        when(reservaRepository.findById(999)).thenReturn(Optional.empty());

        Reserva r = reservaService.buscarPorId(999);

        assertNull(r);
    }

    @Test
    void listarReservas_retornaLista() {
        List<Reserva> lista = List.of(reservaMock, reservaMock);
        when(reservaRepository.findAll()).thenReturn(lista);

        List<Reserva> result = reservaService.listarReservas();

        assertEquals(2, result.size());
        assertIterableEquals(lista, result);
    }

    @Test
    void cancelarReserva_alwaysInvocaDeleteById() {
        doNothing().when(reservaRepository).deleteById(1);

        reservaService.cancelarReserva(1);

        verify(reservaRepository).deleteById(1);
    }
}
