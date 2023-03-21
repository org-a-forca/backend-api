package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.ServicoDto;
import br.com.aforca.admin.domain.entity.Servico;

import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {
  public ServicoDto toDTO(Servico servico) {
    if (servico == null) return null;

    var servicoDto = new ServicoDto();
    servicoDto.setId(servico.getId());
    servicoDto.setNome(servico.getNome());
    servicoDto.setCategoria(servico.getCategoria());

    return servicoDto;
  }

  public Servico toEntity(ServicoDto servicoDto) {
    if (servicoDto == null) return null;

    var servico = new Servico();

    if (servicoDto.getId() != null) servico.setId(servicoDto.getId());
    servico.setNome(servicoDto.getNome());
    servico.setCategoria(servicoDto.getCategoria());

    return servico;
  }
}
