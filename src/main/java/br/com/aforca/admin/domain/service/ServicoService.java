package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.mapper.ServicoMapper;
import br.com.aforca.admin.api.model.NovoServicoDto;
import br.com.aforca.admin.api.model.ServicoDto;
import br.com.aforca.admin.domain.repository.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

  public ServicoDto create(@Valid @NotNull NovoServicoDto novoServicoDto) {
    return servicoMapper.toDTO(servicoRepository.save(servicoMapper.toEntity(novoServicoDto)));
  }

  public ServicoDto getById(@PathVariable @NotNull @Positive Long id) {
    return servicoRepository.findById(id).map(servicoMapper::toDTO).orElseThrow();
  }

  public List<ServicoDto> getAll(Integer pagNum, Integer pagTam) {
    return servicoRepository.findAll(PageRequest.of(pagNum, pagTam)).stream().map(servicoMapper::toDTO).collect(Collectors.toList());
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

  public List<ServicoDto> getAllByNome(Integer pagNum, Integer pagTam, String nome) {
    return servicoRepository.findAllByNome(nome, PageRequest.of(pagNum, pagTam)).stream().map(servicoMapper::toDTO).collect(Collectors.toList());
  }
}
