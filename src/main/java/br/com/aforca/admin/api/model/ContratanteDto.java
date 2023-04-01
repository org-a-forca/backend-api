package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Contratante;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ContratanteDto {
  private Long id;
  private String nome;
  private String telefone;
  private String endereco;
  private String email;
  private LocalDate dataCadastro;
  private String observacoes;

  public ContratanteDto(Contratante contratante) {
    this.id = contratante.getId();
    this.nome = contratante.getNome();
    this.telefone = contratante.getTelefone();
    this.endereco = contratante.getEndereco();
    this.email = contratante.getEmail();
    this.dataCadastro = contratante.getDataCadastro();
    this.observacoes = contratante.getObservacoes();
  }
}
