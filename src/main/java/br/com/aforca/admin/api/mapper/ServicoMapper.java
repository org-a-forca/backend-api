package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.NovoServicoDto;
import br.com.aforca.admin.api.model.ServicoResumoDto;
import br.com.aforca.admin.api.model.ServicoDto;
import br.com.aforca.admin.domain.entity.Categoria;
import br.com.aforca.admin.domain.entity.Servico;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServicoMapper {
  private CategoriaMapper categoriaMapper;

  public ServicoDto toDTO(Servico servico) {
    if (servico == null) return null;

    var servicoDto = new ServicoDto();
    servicoDto.setId(servico.getId());
    servicoDto.setNome(servico.getNome());
    servicoDto.setCategoria(categoriaMapper.toDTO(servico.getCategoria()));

    return servicoDto;
  }

  public ServicoResumoDto toAsElementDTO(Servico servico) {
    if (servico == null) return null;

    var servicoAsElementDto = new ServicoResumoDto();
    servicoAsElementDto.setId(servico.getId());
    servicoAsElementDto.setNome(servico.getNome());
    servicoAsElementDto.setCategoriaNome(servico.getCategoria().getNome());

    return servicoAsElementDto;
  }

  public Servico toEntity(NovoServicoDto novoServicoDto) {
    if (novoServicoDto == null) return null;

    var categoria = new Categoria();
    categoria.setId(novoServicoDto.getCategoriaId());

    var servico = new Servico();
    servico.setNome(novoServicoDto.getNome());
    servico.setCategoria(categoria);

    return servico;
  }
}
