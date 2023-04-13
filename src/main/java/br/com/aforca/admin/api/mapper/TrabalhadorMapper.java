package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.NovoTrabalhadorDto;
import br.com.aforca.admin.api.model.TrabalhadorDto;
import br.com.aforca.admin.api.model.TrabalhadorResumoDto;
import br.com.aforca.admin.domain.entity.Trabalhador;
import org.springframework.stereotype.Component;

@Component
public class TrabalhadorMapper {
  public TrabalhadorDto toDto(Trabalhador trabalhador) {
    return trabalhador == null ? null : new TrabalhadorDto(trabalhador);
  }

  public TrabalhadorResumoDto toResumoDto(Trabalhador trabalhador) {
    return trabalhador == null ? null : new TrabalhadorResumoDto(trabalhador);
  }

  public Trabalhador toEntity(NovoTrabalhadorDto novoTrabalhadorDto) {
    return novoTrabalhadorDto == null ? null : new Trabalhador(novoTrabalhadorDto);
  }
}
