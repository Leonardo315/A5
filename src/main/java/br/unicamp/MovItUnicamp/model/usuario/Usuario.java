package br.unicamp.MovItUnicamp.model.usuario;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Usuario")
@Table(name = "tbusuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String RA;
    private String dataDeNascimento;
    private String telefone;
    private String senha;



    public Usuario(DadosCadastroUsuario dadosCadastroUsuario) {
        this.nome =dadosCadastroUsuario.nome();
        this.email = dadosCadastroUsuario.email();
        this.RA=dadosCadastroUsuario.RA();
        this.dataDeNascimento = dadosCadastroUsuario.dataDeNascimento();
        this.telefone =dadosCadastroUsuario.telefone();
        this.senha = dadosCadastroUsuario.senha();
    }
}