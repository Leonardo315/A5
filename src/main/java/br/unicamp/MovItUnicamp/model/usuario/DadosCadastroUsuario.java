package br.unicamp.MovItUnicamp.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

public record DadosCadastroUsuario (@NotBlank String nome,
                                    @NotBlank @Email String email,
                                    @NotBlank @Pattern(regexp = "^\\d{6}$") String RA,
                                    @NotBlank @DateTimeFormat(pattern = "dd/MM/yyyy") String dataDeNascimento,
                                    @NotBlank @Pattern(regexp = "^\\+55 \\(\\d{2}\\) 9\\d{4}-\\d{4}$", message = "Telefone inválido. Use o formato: +55 (19) 9XXXX-XXXX")String telefone,
                                    @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "A senha deve ter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial") String senha){
}
