package br.com.aforca.aforcaadminapi.services;

import br.com.aforca.aforcaadminapi.dtos.ServicoDto;
import br.com.aforca.aforcaadminapi.dtos.mappers.ServicoMapper;
import br.com.aforca.aforcaadminapi.repositories.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServicoService {
  private final ServicoRepository servicoRepository;
  private final ServicoMapper servicoMapper;

  public ServicoDto create(@Valid @NotNull ServicoDto servicoDto) {
    return servicoMapper.toDTO(servicoRepository.save(servicoMapper.toEntity(servicoDto)));
  }

  public ServicoDto getById(@PathVariable @NotNull @Positive Long id) {
    return servicoRepository.findById(id).map(servicoMapper::toDTO).orElseThrow();
  }

  public List<ServicoDto> getAll() {
    return servicoRepository.findAll().stream().map(servicoMapper::toDTO).collect(Collectors.toList());
  }

  public ServicoDto update(@NotNull @Positive Long id, @Valid @NotNull ServicoDto servicoDto) {
    return servicoRepository.findById(id)
        .map(servicoFound -> {
          servicoFound.setNome(servicoDto.getNome());
          servicoFound.setCategoria(servicoDto.getCategoria());
          return servicoMapper.toDTO(servicoRepository.save(servicoFound));
        }).orElseThrow();
  }

  public void delete(@PathVariable @NotNull @Positive Long id) {
    servicoRepository.delete(servicoRepository.findById(id).orElseThrow());
  }

  public List<ServicoDto> getAllByNome(String nome) {
    return servicoRepository.findAllByNome(nome).stream().map(servicoMapper::toDTO).collect(Collectors.toList());
  }
}
