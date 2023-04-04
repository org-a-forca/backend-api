package br.com.aforca.admin.domain.entity;

import br.com.aforca.admin.api.model.NovaCategoriaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String nome;

  public Categoria(NovaCategoriaDto novaCategoriaDto) {
    this.nome = novaCategoriaDto.getNome();
  }
}
