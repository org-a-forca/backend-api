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
  private String nivel;
  private String mesesUltimoContrato;

  public TrabalhadorResumoDto(Trabalhador trabalhador) {
    this.id = trabalhador.getId();
    this.nome = trabalhador.getNome();
    this.servicosNomes = textoServicos(trabalhador.getServicos());
    this.nivel = textoNivel(trabalhador.getNivel());
    this.mesesUltimoContrato = textoMesesUltimoContrato(trabalhador.getDataUltimoContrato());
  }

  private String textoNivel(Integer nivel) {
    return "Nível " + nivel;
  }

  private String textoMesesUltimoContrato(LocalDate dataUltimoContrato) {
    var dataHoje = LocalDate.now();
    Period periodo = Period.between(dataHoje.withDayOfMonth(1), dataUltimoContrato.withDayOfMonth(1));
    int meses = periodo.getYears() * 12 + periodo.getMonths();

    return meses + " meses";
  }

  private String textoServicos(List<Servico> servicos) {
    List<String> servicosNomes = servicos.stream().map(s -> s.getNome()).collect(Collectors.toList());
    return servicosNomes.toString().substring(1, servicosNomes.toArray().length - 1);
  }
}
