package br.com.aforca.admin.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contrato")
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @OneToOne
  @JoinColumn(name = "contratante_id", referencedColumnName = "id", nullable = false)
  @Getter
  @Setter
  private Contratante contratante;

  @OneToOne
  @JoinColumn(name = "trabalhador_id", referencedColumnName = "id", nullable = false)
  @Getter
  @Setter
  private Trabalhador trabalhador;

  @Getter
  @Setter
  @ManyToMany
  @JoinTable(name = "contratos_servicos", joinColumns = @JoinColumn(name = "contrato_id"), inverseJoinColumns = @JoinColumn(name = "servico_id"))
  private List<Servico> servicosContratados;

  @Getter
  @Setter
  private LocalDate data;

  @Getter
  @Setter
  private String status;

  @Getter
  @Setter
  @Column(name = "nota_trabalhador")
  private Integer notaTrabalhador;

  @Getter
  @Setter
  @Column(name = "nota_contratante")
  private Integer notaContratante;

  @Getter
  @Setter
  private String observacoes;
}
