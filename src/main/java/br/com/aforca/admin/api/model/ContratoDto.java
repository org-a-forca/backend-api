package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Contrato;
import br.com.aforca.admin.domain.entity.Servico;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ContratoDto {
  private Long id;
  private ContratanteDto contratante;
  private TrabalhadorDto trabalhador;
  private List<ServicoDto> servicosContratados;
  private LocalDate data;
  private String status;
  private Integer notaTrabalhador;
  private Integer notaContratante;
  private String observacoes;

  public ContratoDto(Contrato contrato) {
    List<ServicoDto> servicosContratados = new ArrayList<>();
    for (Servico servico : contrato.getServicosContratados()) {
      servicosContratados.add(new ServicoDto(servico));
    }

    this.id = contrato.getId();
    this.contratante = new ContratanteDto(contrato.getContratante());
    this.trabalhador = new TrabalhadorDto(contrato.getTrabalhador());
    this.servicosContratados = servicosContratados;
    this.data = contrato.getData();
    this.status = contrato.getStatus();
    this.notaTrabalhador = contrato.getNotaTrabalhador();
    this.notaContratante = contrato.getNotaContratante();
    this.observacoes = contrato.getObservacoes();
  }
}
