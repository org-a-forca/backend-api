package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.mapper.ContratanteMapper;
import br.com.aforca.admin.api.model.ContratanteAsElementDto;
import br.com.aforca.admin.api.model.ContratanteDto;
import br.com.aforca.admin.api.model.NovoContratanteDto;
import br.com.aforca.admin.domain.repository.ContratanteRepository;
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
public class ContratanteService {
  private final ContratanteRepository contratanteRepository;
  private final ContratanteMapper contratanteMapper;

  public ContratanteDto create(@Valid @NotNull NovoContratanteDto novoContratanteDto) {
    return contratanteMapper.toDto(contratanteRepository.save(contratanteMapper.toEntity(novoContratanteDto)));
  }

  public ContratanteDto getById(@PathVariable @NotNull @Positive Long id) {
    return contratanteRepository.findById(id).map(contratanteMapper::toDto).orElseThrow();
  }

  public List<ContratanteAsElementDto> getAll(String nome, String telefone, Integer pagNum, Integer pagTam) {
    if (nome != null && !nome.isBlank())
      return contratanteRepository.findAllByNome(nome, PageRequest.of(pagNum, pagTam)).stream().map(contratanteMapper::toAsElementDto).collect(Collectors.toList());
    else if (telefone != null && !telefone.isBlank())
      return contratanteRepository.findAllByTelefone(telefone, PageRequest.of(pagNum, pagTam)).stream().map(contratanteMapper::toAsElementDto).collect(Collectors.toList());
    else
      return contratanteRepository.findAll(PageRequest.of(pagNum, pagTam)).stream().map(contratanteMapper::toAsElementDto).collect(Collectors.toList());
  }

  public Long getAllQuantidade(String nome, String telefone) {
    if (nome != null && !nome.isBlank())
      return contratanteRepository.findAllByNomeQuantidade(nome);
    else if (telefone != null && !telefone.isBlank())
      return contratanteRepository.findAllByTelefoneQuantidade(telefone);
    else
      return contratanteRepository.count();
  }

  public ContratanteDto update(@NotNull @Positive Long id, @Valid @NotNull NovoContratanteDto novoContratanteDto) {
    return contratanteRepository.findById(id)
        .map(contratanteFound -> {
          contratanteFound.setNome(novoContratanteDto.getNome());
          contratanteFound.setTelefone(novoContratanteDto.getTelefone());
          contratanteFound.setEndereco(novoContratanteDto.getEndereco());
          contratanteFound.setEmail(novoContratanteDto.getEmail());
          contratanteFound.setObservacoes(novoContratanteDto.getObservacoes());

          return contratanteMapper.toDto(contratanteRepository.save(contratanteFound));
        }).orElseThrow();
  }

  public void delete(@PathVariable @NotNull @Positive Long id) {
    contratanteRepository.delete(contratanteRepository.findById(id).orElseThrow());
  }
}
