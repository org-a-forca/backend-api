package br.com.aforca.aforcaadminapi.dtos.mappers;

import br.com.aforca.aforcaadminapi.dtos.CategoriaDto;
import br.com.aforca.aforcaadminapi.models.Categoria;
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
}
