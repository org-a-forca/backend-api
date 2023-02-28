package br.com.aforca.aforcaadminapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "servico")
public class Servico {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Getter
  @Setter
  private Long id;

  @NotNull
  @Column(name = "nome", length = 75, nullable = false)
  @Getter
  @Setter
  private String nome;

  @OneToOne
  @JoinColumn(name = "categoria_servico_id")
  @Getter
  @Setter
  private CategoriaServico categoriaServico;
}
