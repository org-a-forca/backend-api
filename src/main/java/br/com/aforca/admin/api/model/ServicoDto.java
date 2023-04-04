package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Servico;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoDto {
  private Long id;
  private String nome;
  private CategoriaDto categoria;

  public ServicoDto(Servico servico) {
    this.id = servico.getId();
    this.nome = servico.getNome();
    this.categoria = new CategoriaDto(servico.getCategoria());
  }
}
