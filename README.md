## Avaliação A5 – Testes Automatizados

**Critérios aplicados**  
- **Particionamento em classes de equivalência**  
- **Análise de valor-limite**  

**Arquivos de teste**  
- `src/test/java/br/unicamp/MovItUnicamp/service/PontuacaoServiceTest.java`  
- `src/test/java/br/unicamp/MovItUnicamp/service/CorridaServiceTest.java`  
- `src/test/java/br/unicamp/MovItUnicamp/service/ReservaServiceTest.java`  

### PontuacaoServiceTest.java  
| Método de Teste                                                      | Classe de Equivalência         | Valores-limite exercitados       |
|----------------------------------------------------------------------|--------------------------------|----------------------------------|
| `gerarPontuacao_deveSalvarCom10PontosPorKm_quandoDistanciaPositiva()` | CE: distância > 0              | 2.3 km (→ 23 pontos)             |
| `gerarPontuacao_deveGerarZeroPontos_quandoDistanciaNaoPositiva()`     | CE: distância ≤ 0              | 0.0 km                           |

### CorridaServiceTest.java  
| Método de Teste                                      | Classe de Equivalência     | Valores-limite exercitados |
|------------------------------------------------------|----------------------------|----------------------------|
| `deveIniciarCorrida_comDistanciaPositiva()`          | CE: distância > 0          | 5.0 km                     |
| `deveIniciarCorrida_comDistanciaZero()`              | CE: distância = 0          | 0.0 km                     |
| `deveIniciarCorrida_comDistanciaNegativa()`          | CE: distância < 0          | –0.1 km                    |
| `deveEncerrarCorrida_comDistanciaPositiva()`         | CE: distância > 0          | 12.5 km                    |
| `deveEncerrarCorrida_comDistanciaZero()`             | CE: distância = 0          | 0.0 km                     |
| `deveEncerrarCorrida_comDistanciaNegativa()`         | CE: distância < 0          | –5.0 km                    |

### ReservaServiceTest.java  
| Método de Teste                                            | Classe de Equivalência       | Valores-limite exercitados |
|------------------------------------------------------------|-------------------------------|----------------------------|
| `criarReserva_validos_retornaReserva()`                    | CE: usuário existe            | `usuarioId = 1`            |
| `criarReserva_usuarioInexistente_salvaComUsuarioNull()`    | CE: usuário não existe        | `usuarioId = 0`            |
| `buscarPorId_existente_retornaReserva()`                   | CE: ID válido                 | `id = 1`                   |
| `buscarPorId_inexistente_retornaNull()`                    | CE: ID inválido               | `id = 999`                 |
| `listarReservas_retornaLista()`                            | CE: retorno de lista não-vazia| —                          |
| `cancelarReserva_alwaysInvocaDeleteById()`                 | CE: operação de delete        | —                          |

---

### Evidência de Execução

![Suíte de testes passando](https://github.com/Leonardo315/A5/blob/main/src/main/resources/EvidenciaTestesPassadosComSucesso.png)

--- 

> Todos os casos de teste acima exercitam claramente classes de equivalência e valores-limite, conforme os critérios aprendidos em sala e nos materiais de referência.  
