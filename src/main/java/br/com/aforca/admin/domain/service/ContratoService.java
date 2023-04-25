package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.mapper.ContratoMapper;
import br.com.aforca.admin.api.model.ContratoDto;
import br.com.aforca.admin.api.model.ContratoResumoDto;
import br.com.aforca.admin.api.model.NovoContratoDto;
import br.com.aforca.admin.domain.entity.Servico;
import br.com.aforca.admin.domain.repository.ContratanteRepository;
import br.com.aforca.admin.domain.repository.ContratoRepository;
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
public class ContratoService {
  private final ContratoRepository contratoRepository;
  private final ContratoMapper contratoMapper;
  private final ServicoRepository servicoRepository;
  private final ContratanteRepository contratanteRepository;
  private final TrabalhadorRepository trabalhadorRepository;

  public ContratoDto create(@Valid @NotNull NovoContratoDto novoContratoDto) {
    var contrato = contratoRepository.save(contratoMapper.toEntity(novoContratoDto));
    contrato.setServicosContratados(listaServicos(novoContratoDto.getServicosContratadosIds()));

    return contratoMapper.toDto(contrato);
  }

  public ContratoDto getById(@PathVariable @NotNull @Positive Long id) {
    return contratoRepository.findById(id).map(contratoMapper::toDto).orElseThrow();
  }

  public List<ContratoResumoDto> getAll(Integer pagNum, Integer pagTam) {
    return contratoRepository.findAll(PageRequest.of(pagNum, pagTam)).stream().map(contratoMapper::toResumoDto).collect(Collectors.toList());
  }

  public Long getAllQuantidade() {
    return contratoRepository.count();
  }

  public ContratoDto update(@NotNull @Positive Long id, @Valid @NotNull NovoContratoDto novoContratoDto) {
    return contratoRepository.findById(id)
        .map(contratoFound -> {
          contratoFound.setContratante(contratanteRepository.findById(novoContratoDto.getContratanteId()).get());
          contratoFound.setTrabalhador(trabalhadorRepository.findById(novoContratoDto.getTrabalhadorId()).get());
          contratoFound.setServicosContratados(listaServicos(novoContratoDto.getServicosContratadosIds()));
          contratoFound.setStatus(novoContratoDto.getStatus());
          contratoFound.setNotaTrabalhador(novoContratoDto.getNotaTrabalhador());
          contratoFound.setNotaContratante(novoContratoDto.getNotaContratante());
          contratoFound.setObservacoes(novoContratoDto.getObservacoes());

          return contratoMapper.toDto(contratoRepository.save(contratoFound));
        }).orElseThrow();
  }

  public void delete(@PathVariable @NotNull @Positive Long id) {
    contratoRepository.delete(contratoRepository.findById(id).orElseThrow());
  }

  private List<Servico> listaServicos(List<Long> servicosIds) {
    List<Servico> servicos = new ArrayList<>();
    for (Long id : servicosIds) {
      servicos.add(servicoRepository.findById(id).get());
    }

    return servicos;
  }
}
