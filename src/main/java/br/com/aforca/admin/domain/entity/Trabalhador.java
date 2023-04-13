package br.com.aforca.admin.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trabalhador")
@NoArgsConstructor
@AllArgsConstructor
public class Trabalhador {
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
  private Integer nivel;

  @Getter
  @Setter
  @Column(name = "data_cadastro")
  private LocalDate dataCadastro;

  @Getter
  @Setter
  @Column(name = "data_ultimo_contrato")
  private LocalDate dataUltimoCadastro;

  @Getter
  @Setter
  private String referencias;

  @Getter
  @Setter
  private String restricoes;

  @Getter
  @Setter
  private String observacoes;

  @Getter
  @Setter
  @ManyToMany
  @JoinTable(name = "trabalhadores_servicos", joinColumns = @JoinColumn(name = "trabalhador_id"), inverseJoinColumns = @JoinColumn(name = "servico_id"))
  private List<Servico> servicos;
}
