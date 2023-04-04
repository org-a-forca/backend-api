package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.CategoriaDto;
import br.com.aforca.admin.api.model.NovaCategoriaDto;
import br.com.aforca.admin.domain.entity.Categoria;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
  public CategoriaDto toDto(Categoria categoria) {
    return categoria == null ? null : new CategoriaDto(categoria);
  }

  public Categoria toEntity(NovaCategoriaDto novaCategoriaDto) {
    return novaCategoriaDto == null ? null : new Categoria(novaCategoriaDto);
  }
}
