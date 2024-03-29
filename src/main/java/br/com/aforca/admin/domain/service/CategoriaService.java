package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.exception.NomeJaRegistradoException;
import br.com.aforca.admin.api.mapper.CategoriaMapper;
import br.com.aforca.admin.api.model.CategoriaDto;
import br.com.aforca.admin.api.model.NovaCategoriaDto;
import br.com.aforca.admin.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaDto create(@Valid @NotNull NovaCategoriaDto novaCategoriaDto) {
        if (categoriaRepository.findByNome(novaCategoriaDto.getNome()) != null)
            throw new NomeJaRegistradoException("Nome da categoria já registrado");

        return categoriaMapper.toDto(categoriaRepository.save(categoriaMapper.toEntity(novaCategoriaDto)));
    }

    public CategoriaDto getById(@PathVariable @NotNull @Positive Long id) {
        return categoriaRepository.findById(id).map(categoriaMapper::toDto).orElseThrow();
    }

    public List<CategoriaDto> getAll() {
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).collect(Collectors.toList());
    }

    public CategoriaDto update(@NotNull @Positive Long id, @Valid @NotNull CategoriaDto categoriaDto) {
        var categoria = categoriaRepository.findByNome(categoriaDto.getNome());

        if (categoria != null && !categoria.getId().equals(id))
            throw new NomeJaRegistradoException("Já existe uma categoria com esse nome");

        return categoriaRepository.findById(id)
            .map(categoriaFound -> {
                categoriaFound.setNome(categoriaDto.getNome());
                return categoriaMapper.toDto(categoriaRepository.save(categoriaFound));
            }).orElseThrow();
    }

    public void detele(@PathVariable @NotNull @Positive Long id) {
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow());
    }
}
