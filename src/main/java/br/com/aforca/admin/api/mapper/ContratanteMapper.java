package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.ContratanteResumoDto;
import br.com.aforca.admin.api.model.ContratanteDto;
import br.com.aforca.admin.api.model.NovoContratanteDto;
import br.com.aforca.admin.domain.entity.Contratante;
import org.springframework.stereotype.Component;

@Component
public class ContratanteMapper {
  public ContratanteDto toDto(Contratante contratante) {
    return contratante == null ? null : new ContratanteDto(contratante);
  }

  public ContratanteResumoDto toAsElementDto(Contratante contratante) {
    return contratante == null ? null : new ContratanteResumoDto(contratante);
  }

  public Contratante toEntity(NovoContratanteDto novoContratanteDto) {
    return novoContratanteDto == null ? null : new Contratante(novoContratanteDto);
  }
}
