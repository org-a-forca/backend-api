package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Servico;
import br.com.aforca.admin.domain.entity.Trabalhador;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TrabalhadorResumoDto {
  private Long id;
  private String nome;
  private String servicosNomes;
  private Integer nivel;
  private Integer mesesUltimoContrato;

  public TrabalhadorResumoDto(Trabalhador trabalhador) {
    this.id = trabalhador.getId();
    this.nome = trabalhador.getNome();
    this.servicosNomes = textoServicos(trabalhador.getServicos());
    this.nivel = trabalhador.getNivel();
    this.mesesUltimoContrato = mesesUltimoContrato(trabalhador.getDataUltimoContrato());
  }

  private Integer mesesUltimoContrato(LocalDate dataUltimoContrato) {
    if (dataUltimoContrato == null) return 0;

    var dataHoje = LocalDate.now();
    var periodo = Period.between(dataHoje.withDayOfMonth(1), dataUltimoContrato.withDayOfMonth(1));

    return periodo.getYears() * 12 + periodo.getMonths();
  }

  private String textoServicos(List<Servico> servicos) {
    List<String> servicosNomes = servicos.stream().map(s -> s.getNome()).collect(Collectors.toList());
    return servicosNomes.toString().substring(1, servicosNomes.toString().length() - 1);
  }
}
