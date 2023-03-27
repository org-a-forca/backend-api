package br.com.aforca.admin.api.mapper;

import br.com.aforca.admin.api.model.CategoriaDto;
import br.com.aforca.admin.api.model.NovaCategoriaDto;
import br.com.aforca.admin.domain.entity.Categoria;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
  public CategoriaDto toDTO(Categoria categoria) {
    if (categoria == null) return null;

    var categoriaDto = new CategoriaDto();
    categoriaDto.setId(categoria.getId());
    categoriaDto.setNome(categoria.getNome());

    return categoriaDto;
  }

  public Categoria toEntity(CategoriaDto categoriaDto) {
    if (categoriaDto == null) return null;

    var categoria = new Categoria();

    if (categoriaDto.getId() != null) categoria.setId(categoriaDto.getId());
    categoria.setNome(categoriaDto.getNome());

    return categoria;
  }

  public Categoria toEntity(NovaCategoriaDto novaCategoriaDto) {
    var categoria = new Categoria();
    categoria.setId(null);
    categoria.setNome(novaCategoriaDto.getNome());

    return categoria;
  }
}
