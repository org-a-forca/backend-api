package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Contratante;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContratanteAsElementDto {
  private Long id;
  private String nome;
  private String telefone;

  public ContratanteAsElementDto(Contratante contratante) {
    this.id = contratante.getId();
    this.nome = contratante.getNome();
    this.telefone = contratante.getTelefone();
  }
}
