package br.unicamp.MovItUnicamp.model.bicicletas;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroBicicleta(@NotBlank int numeroDeSerie,
                                     @NotBlank String marca,
                                     @NotBlank String modelo) {
}
