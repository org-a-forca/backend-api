package br.com.aforca.aforcaadminapi.services;

import br.com.aforca.aforcaadminapi.dtos.CategoriaDto;
import br.com.aforca.aforcaadminapi.dtos.mappers.CategoriaMapper;
import br.com.aforca.aforcaadminapi.repositories.CategoriaRepository;
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

    public CategoriaDto create(@Valid @NotNull CategoriaDto categoriaDto) {
        return categoriaMapper.toDTO(categoriaRepository.save(categoriaMapper.toEntity(categoriaDto)));
    }

    public CategoriaDto getById(@PathVariable @NotNull @Positive Long id) {
        return categoriaRepository.findById(id).map(categoriaMapper::toDTO).orElseThrow();
    }

    public List<CategoriaDto> getAll() {
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDTO).collect(Collectors.toList());
    }

    public CategoriaDto update(@NotNull @Positive Long id, @Valid @NotNull CategoriaDto categoriaDto) {
        return categoriaRepository.findById(id)
            .map(categoriaFound -> {
                categoriaFound.setNome(categoriaDto.getNome());
                return categoriaMapper.toDTO(categoriaRepository.save(categoriaFound));
            }).orElseThrow();
    }

    public void detele(@PathVariable @NotNull @Positive Long id) {
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow());
    }
}
