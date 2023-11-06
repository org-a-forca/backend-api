package br.com.aforca.admin.domain.entity;

import br.com.aforca.admin.api.model.NovoServicoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "servico")
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String nome;

  @ManyToOne
  @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
  @Getter
  @Setter
  private Categoria categoria;

  @ManyToMany(mappedBy = "servicos")
  private List<Trabalhador> trabalhadores;

  @ManyToMany(mappedBy = "servicosContratados")
  private List<Contrato> contratos;

  public Servico(NovoServicoDto novoServicoDto) {
    var categoria = new Categoria();
    categoria.setId(novoServicoDto.getCategoriaId());

    this.nome = novoServicoDto.getNome();
    this.categoria = categoria;
  }
}
