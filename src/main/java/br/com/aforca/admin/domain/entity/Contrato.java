package br.com.aforca.admin.domain.entity;

import br.com.aforca.admin.api.model.NovoContratoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

  public Contrato(NovoContratoDto novoContratoDto) {
    var contratante = new Contratante();
    contratante.setId(novoContratoDto.getContratanteId());

    var trabalhador = new Trabalhador();
    trabalhador.setId(novoContratoDto.getTrabalhadorId());

    List<Servico> servicosContratados = new ArrayList<>();
    for (Long id : novoContratoDto.getServicosContratadosIds()) {
      var servico = new Servico();
      servico.setId(id);
      servicosContratados.add(servico);
    }

    this.contratante = contratante;
    this.trabalhador = trabalhador;
    this.servicosContratados = servicosContratados;
    this.data = LocalDate.now();
    this.status = novoContratoDto.getStatus();
    this.notaTrabalhador = novoContratoDto.getNotaTrabalhador();
    this.notaContratante = novoContratoDto.getNotaContratante();
    this.observacoes = novoContratoDto.getObservacoes();
  }
}
