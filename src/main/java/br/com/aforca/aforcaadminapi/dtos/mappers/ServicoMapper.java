package br.com.aforca.aforcaadminapi.dtos.mappers;

import br.com.aforca.aforcaadminapi.dtos.CategoriaDto;
import br.com.aforca.aforcaadminapi.dtos.ServicoDto;
import br.com.aforca.aforcaadminapi.models.Categoria;
import br.com.aforca.aforcaadminapi.models.Servico;
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
