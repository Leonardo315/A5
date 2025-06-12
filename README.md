## Avaliação A5 – Testes Automatizados

**Critérios aplicados**  
- **Particionamento em classes de equivalência**  
- **Análise de valor-limite**  

**Arquivos de teste**  
- `src/test/java/br/unicamp/MovItUnicamp/service/PontuacaoServiceTest.java`  
- `src/test/java/br/unicamp/MovItUnicamp/service/CorridaServiceTest.java`  
- `src/test/java/br/unicamp/MovItUnicamp/service/ReservaServiceTest.java`  

### PontuacaoServiceTest.java  
| Método de Teste                                    | Classe de Equivalência         | Valores limite exercitados |
|----------------------------------------------------|---------------------------------|-----------------------------|
| `gerarPontuacao_deveSalvarCom10PontosPorKm_quandoDistanciaPositiva()` | CE positiva                     | +ε (2.3 km → 23 pontos)     |
| `gerarPontuacao_deveGerarZeroPontos_quandoDistanciaNaoPositiva()`    | CE zero ou negativa             | 0 km, −ε (0.0 km)           |

### CorridaServiceTest.java  
| Método de Teste                                      | Classe de Equivalência     | Valores limite exercitados |
|------------------------------------------------------|-----------------------------|-----------------------------|
| `deveIniciarCorrida_comDistanciaPositiva()`          | CE positiva                 | +ε (5.0 km)                 |
| `deveIniciarCorrida_comDistanciaZero()`              | CE zero                     | 0 km                        |
| `deveIniciarCorrida_comDistanciaNegativa()`          | CE negativa                 | −ε (−0.1 km)                |
| `deveEncerrarCorrida_comDistanciaPositiva()`         | CE positiva                 | +ε (12.5 km)                |
| `deveEncerrarCorrida_comDistanciaZero()`             | CE zero                     | 0 km                        |
| `deveEncerrarCorrida_comDistanciaNegativa()`         | CE negativa                 | −ε (−5.0 km)                |

### ReservaServiceTest.java  
| Método de Teste                           | Classe de Equivalência       | Valores limite exercitados |
|-------------------------------------------|-------------------------------|-----------------------------|
| `criarReserva_validos_retornaReserva()`          | CE usuário existe            | usuárioId = 1               |
| `criarReserva_usuarioInexistente_retornaReservaComUsuarioNull()` | CE usuário não existe       | usuárioId = 0               |
| `buscarPorId_existente_retornaReserva()`         | CE ID válido                 | id = 1                      |
| `buscarPorId_inexistente_retornaNull()`          | CE ID inválido               | id = 999                    |
| `listarReservas_retornaLista()`                  | CE lista não-vazia           | —                           |
| `cancelarReserva_alwaysInvocaDeleteById()`       | CE operação deletar          | —                           |

---

### Evidência de Execução

![Suíte de testes passando](docs/tests-passing.png)

---

**Issue**: `AvaliacaoA5`  
**Branch/PR**: `feature/AvaliacaoA5-tests`  

> Todos os casos de teste acima exercitam claramente classes de equivalência e valores-limite, conforme os critérios aprendidos em sala e nos slides/textos de referência.
