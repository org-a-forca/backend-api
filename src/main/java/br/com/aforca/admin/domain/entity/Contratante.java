package br.com.aforca.admin.domain.entity;

import br.com.aforca.admin.api.model.NovoContratanteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contratante")
@NoArgsConstructor
@AllArgsConstructor
public class Contratante {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String nome;

  @Getter
  @Setter
  private String telefone;

  @Getter
  @Setter
  private String endereco;

  @Getter
  @Setter
  private String email;

  @Getter
  @Setter
  @Column(name = "data_cadastro")
  private LocalDate dataCadastro;

  @Getter
  @Setter
  private String observacoes;

  public Contratante(NovoContratanteDto novoContratanteDto) {
    this.nome = novoContratanteDto.getNome();
    this.telefone = novoContratanteDto.getTelefone();
    this.endereco = novoContratanteDto.getEndereco();
    this.email = novoContratanteDto.getEmail();
    this.dataCadastro = LocalDate.now();
    this.observacoes = novoContratanteDto.getObservacoes();
  }
}
