package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.exception.ServicoNaoVinculadorAoTrabalhadorException;
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
  private final TrabalhadorService trabalhadorService;

  public ContratoDto create(@Valid @NotNull NovoContratoDto novoContratoDto) {
    verificaServicosTrabalhador(novoContratoDto.getTrabalhadorId(), novoContratoDto.getServicosContratadosIds());

    var contrato = contratoRepository.save(contratoMapper.toEntity(novoContratoDto));
    contrato.setTrabalhador(trabalhadorRepository.findById(novoContratoDto.getTrabalhadorId()).get());
    contrato.setContratante(contratanteRepository.findById(novoContratoDto.getContratanteId()).get());
    contrato.setServicosContratados(listaServicos(novoContratoDto.getServicosContratadosIds()));

    trabalhadorService.atualizaDataUltimoContrato(contrato.getTrabalhador().getId(), contrato.getData());

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
    verificaServicosTrabalhador(novoContratoDto.getTrabalhadorId(), novoContratoDto.getServicosContratadosIds());

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

  public void verificaServicosTrabalhador(Long idTrabalhor, List<Long> servicosIds) {
    var trabalhador = trabalhadorRepository.findById(idTrabalhor).get();
    List<Servico> servicosTrabalhador = trabalhador.getServicos();

    for (Long servicoId : servicosIds) {
      if (servicosTrabalhador.stream().noneMatch(s -> s.getId().equals(servicoId)))
          throw new ServicoNaoVinculadorAoTrabalhadorException("O serviço de ID " + servicoId + " não está vinculado a este trabalhador");
    }
  }
}
