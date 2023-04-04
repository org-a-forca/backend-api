package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Servico;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoResumoDto {
  private Long id;
  private String nome;
  private String categoriaNome;

  public ServicoResumoDto(Servico servico) {
    this.id = servico.getId();
    this.nome = servico.getNome();
    this.categoriaNome = servico.getCategoria().getNome();
  }
}
