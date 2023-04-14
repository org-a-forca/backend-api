package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.mapper.TrabalhadorMapper;
import br.com.aforca.admin.api.model.NovoTrabalhadorDto;
import br.com.aforca.admin.api.model.TrabalhadorDto;
import br.com.aforca.admin.api.model.TrabalhadorResumoDto;
import br.com.aforca.admin.domain.entity.Servico;
import br.com.aforca.admin.domain.repository.ServicoRepository;
import br.com.aforca.admin.domain.repository.TrabalhadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrabalhadorService {
  private final TrabalhadorRepository trabalhadorRepository;
  private final TrabalhadorMapper trabalhadorMapper;
  private final ServicoRepository servicoRepository;

  public TrabalhadorDto create(@Valid @NotNull NovoTrabalhadorDto novoTrabalhadorDto) {
    var trabalhador = trabalhadorRepository.save(trabalhadorMapper.toEntity(novoTrabalhadorDto));
    trabalhador.setServicos(listaServicos(novoTrabalhadorDto.getServicosIds()));

    return trabalhadorMapper.toDto(trabalhador);
  }

  public TrabalhadorDto getById(@PathVariable @NotNull @Positive Long id) {
    return trabalhadorRepository.findById(id).map(trabalhadorMapper::toDto).orElseThrow();
  }

  public List<TrabalhadorResumoDto> getAll(String nome, String servico, Integer pagNum, Integer pagTam) {
    if (nome != null && !nome.isBlank()) {
      return trabalhadorRepository.findAllByNome(nome, PageRequest.of(pagNum, pagTam)).stream().map(trabalhadorMapper::toResumoDto).collect(Collectors.toList());
    } else if (servico != null && !servico.isBlank()) {
      List<Long> idsServicos = servicoRepository.findIdsAllByNome(servico);

      if (idsServicos.isEmpty()) return new ArrayList<>();

      return trabalhadorRepository.findAllByIdsServicos(idsServicos, PageRequest.of(pagNum, pagTam)).stream().map(trabalhadorMapper::toResumoDto).collect(Collectors.toList());
    } else {
      return trabalhadorRepository.findAll(PageRequest.of(pagNum, pagTam)).stream().map(trabalhadorMapper::toResumoDto).collect(Collectors.toList());
    }
  }

  public Long getAllQuantidade(String nome, String servico) {
    if (nome != null && !nome.isBlank())
      return trabalhadorRepository.findAllByNomeQuantidade(nome);
    else
      return trabalhadorRepository.count();
  }

  public TrabalhadorDto update(@NotNull @Positive Long id, @Valid @NotNull NovoTrabalhadorDto novoTrabalhadorDto) {
    return trabalhadorRepository.findById(id)
        .map(trabalhadorFound -> {
          trabalhadorFound.setNome(novoTrabalhadorDto.getNome());
          trabalhadorFound.setTelefone(novoTrabalhadorDto.getTelefone());
          trabalhadorFound.setEndereco(novoTrabalhadorDto.getEndereco());
          trabalhadorFound.setEmail(novoTrabalhadorDto.getEmail());
          trabalhadorFound.setReferencias(novoTrabalhadorDto.getReferencias());
          trabalhadorFound.setRestricoes(novoTrabalhadorDto.getRestricoes());
          trabalhadorFound.setServicos(listaServicos(novoTrabalhadorDto.getServicosIds()));

          return trabalhadorMapper.toDto(trabalhadorRepository.save(trabalhadorFound));
        }).orElseThrow();
  }

  public void delete(@PathVariable @NotNull @Positive Long id) {
    trabalhadorRepository.delete(trabalhadorRepository.findById(id).orElseThrow());
  }

  private List<Servico> listaServicos(List<Long> servicosIds) {
    List<Servico> servicos = new ArrayList<>();
    for (Long id : servicosIds) {
      servicos.add(servicoRepository.findById(id).get());
    }

    return servicos;
  }
}
