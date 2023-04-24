package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Contrato;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ContratoResumoDto {
  private String nomeTrabalhador;
  private LocalDate dataContrato;

  public ContratoResumoDto(Contrato contrato) {
    this.nomeTrabalhador = contrato.getTrabalhador().getNome();
    this.dataContrato = contrato.getData();
  }
}
