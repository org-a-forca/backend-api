package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDto {
  private Long id;
  private String nome;

  public CategoriaDto(Categoria categoria) {
    this.id = categoria.getId();
    this.nome = categoria.getNome();
  }
}
