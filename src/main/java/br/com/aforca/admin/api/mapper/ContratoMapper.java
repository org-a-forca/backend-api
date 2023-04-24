package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.ContratoDto;
import br.com.aforca.admin.api.model.ContratoResumoDto;
import br.com.aforca.admin.api.model.NovoContratoDto;
import br.com.aforca.admin.domain.entity.Contrato;
import org.springframework.stereotype.Component;

@Component
public class ContratoMapper {
  public ContratoDto toDto(Contrato contrato) {
    return contrato == null ? null : new ContratoDto(contrato);
  }

  public ContratoResumoDto toResumoDto(Contrato contrato) {
    return contrato == null ? null : new ContratoResumoDto(contrato);
  }

  public Contrato toEntity(NovoContratoDto novoContratoDto) {
    return novoContratoDto == null ? null : new Contrato(novoContratoDto);
  }
}
