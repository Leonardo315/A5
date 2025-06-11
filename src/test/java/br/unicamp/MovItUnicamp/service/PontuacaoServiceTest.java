package br.unicamp.MovItUnicamp.service;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.corrida.CorridaRepository;
import br.unicamp.MovItUnicamp.model.pontuacao.Pontuacao;
import br.unicamp.MovItUnicamp.model.pontuacao.PontuacaoRepository;
import br.unicamp.MovItUnicamp.model.reserva.Reserva;
import br.unicamp.MovItUnicamp.model.service.PontuacaoService;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PontuacaoServiceTest {

    @Mock private CorridaRepository corridaRepository;
    @Mock private PontuacaoRepository pontuacaoRepository;
    @InjectMocks private PontuacaoService service;

    @Test
    void gerarPontuacao_deveSalvarCom10PontosPorKm_quandoDistanciaPositiva() {
        int corridaId = 42;
        Corrida corrida = mock(Corrida.class);
        Reserva reserva = mock(Reserva.class);
        Usuario usuario = mock(Usuario.class);

        when(corridaRepository.findById(corridaId)).thenReturn(Optional.of(corrida));
        when(corrida.getReserva()).thenReturn(reserva);
        when(reserva.getUsuario()).thenReturn(usuario);
        when(corrida.getDistanciaPercorrida()).thenReturn(2.3);

        Pontuacao esperado = new Pontuacao(usuario, corrida, 23);
        when(pontuacaoRepository.save(any())).thenReturn(esperado);

        Pontuacao atual = service.gerarPontuacao(corridaId);

        assertEquals(23, atual.getPontos());
        verify(pontuacaoRepository).save(any(Pontuacao.class));
    }

    @Test
    void gerarPontuacao_deveGerarZeroPontos_quandoDistanciaNaoPositiva() {
        int corridaId = 43;
        Corrida corrida = mock(Corrida.class);
        Reserva reserva = mock(Reserva.class);
        Usuario usuario = mock(Usuario.class);

        when(corridaRepository.findById(corridaId)).thenReturn(Optional.of(corrida));
        when(corrida.getReserva()).thenReturn(reserva);
        when(reserva.getUsuario()).thenReturn(usuario);
        when(corrida.getDistanciaPercorrida()).thenReturn(0.0);

        Pontuacao esperado = new Pontuacao(usuario, corrida, 0);
        when(pontuacaoRepository.save(any())).thenReturn(esperado);

        Pontuacao atual = service.gerarPontuacao(corridaId);

        assertEquals(0, atual.getPontos());
        verify(pontuacaoRepository).save(any(Pontuacao.class));
    }

    @Test
    void listarPontuacoes_deveRetornarTudo_doRepositório() {
        Pontuacao p1 = new Pontuacao(null, null, 1);
        Pontuacao p2 = new Pontuacao(null, null, 2);
        List<Pontuacao> lista = List.of(p1, p2);
        when(pontuacaoRepository.findAll()).thenReturn(lista);

        List<Pontuacao> atual = service.listarPontuacoes();

        assertEquals(2, atual.size());
        assertSame(lista, atual);
        verify(pontuacaoRepository).findAll();
    }

    @Test
    void excluirPontuacao_deveChamarDeletePorId() {
        int id = 99;
        doNothing().when(pontuacaoRepository).deleteById(id);

        service.excluirPontuacao(id);

        verify(pontuacaoRepository).deleteById(id);
    }
}
