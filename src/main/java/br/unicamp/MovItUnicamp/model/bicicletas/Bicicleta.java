package br.unicamp.MovItUnicamp.model.bicicletas;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Bicileta")
@Table(name = "tbbicileta")
public class Bicicleta {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private int numeroDeSerie;
     private String marca;
     private String modelo;

     public Bicicleta(DadosCadastroBicicleta dadosCadastroBicicleta){
         this.numeroDeSerie = dadosCadastroBicicleta.numeroDeSerie();
         this.marca = dadosCadastroBicicleta.marca();
         this.modelo = dadosCadastroBicicleta.modelo();
     }



}
