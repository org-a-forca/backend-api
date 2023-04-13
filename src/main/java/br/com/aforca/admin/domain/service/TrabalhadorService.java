package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.mapper.TrabalhadorMapper;
import br.com.aforca.admin.api.model.NovoTrabalhadorDto;
import br.com.aforca.admin.api.model.TrabalhadorDto;
import br.com.aforca.admin.domain.entity.Servico;
import br.com.aforca.admin.domain.repository.ServicoRepository;
import br.com.aforca.admin.domain.repository.TrabalhadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

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

  private List<Servico> listaServicos(List<Long> servicosIds) {
    List<Servico> servicos = new ArrayList<>();
    for (Long id : servicosIds) {
      servicos.add(servicoRepository.findById(id).get());
    }

    return servicos;
  }
}
