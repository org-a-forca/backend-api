package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.NovoServicoDto;
import br.com.aforca.admin.api.model.ServicoResumoDto;
import br.com.aforca.admin.api.model.ServicoDto;
import br.com.aforca.admin.domain.entity.Servico;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServicoMapper {
  private CategoriaMapper categoriaMapper;

  public ServicoDto toDto(Servico servico) {
    return servico == null ? null : new ServicoDto(servico);
  }

  public ServicoResumoDto toResumoDto(Servico servico) {
    return servico == null ? null : new ServicoResumoDto(servico);
  }

  public Servico toEntity(NovoServicoDto novoServicoDto) {
    return novoServicoDto == null ? null : new Servico(novoServicoDto);
  }
}
