package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.exception.NomeJaRegistradoException;
import br.com.aforca.admin.api.mapper.ServicoMapper;
import br.com.aforca.admin.api.model.NovoServicoDto;
import br.com.aforca.admin.api.model.ServicoResumoDto;
import br.com.aforca.admin.api.model.ServicoDto;
import br.com.aforca.admin.domain.repository.CategoriaRepository;
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
  private final CategoriaRepository categoriaRepository;
  private final ServicoMapper servicoMapper;
  private final CategoriaService categoriaService;

  public ServicoDto create(@Valid @NotNull NovoServicoDto novoServicoDto) {
    if (servicoRepository.findByNome(novoServicoDto.getNome()) != null)
      throw new NomeJaRegistradoException("Nome do serviço já registrado");

    var servicodto = servicoMapper.toDTO(servicoRepository.save(servicoMapper.toEntity(novoServicoDto)));
    servicodto.setCategoria(categoriaService.getById(novoServicoDto.getCategoriaId()));

    return servicodto;
  }

  public ServicoDto getById(@PathVariable @NotNull @Positive Long id) {
    return servicoRepository.findById(id).map(servicoMapper::toDTO).orElseThrow();
  }

  public List<ServicoResumoDto> getAll(String nome, Integer pagNum, Integer pagTam) {
    if (nome != null && !nome.isBlank())
      return servicoRepository.findAllByNome(nome, PageRequest.of(pagNum, pagTam)).stream().map(servicoMapper::toAsElementDTO).collect(Collectors.toList());
    else
      return servicoRepository.findAll(PageRequest.of(pagNum, pagTam)).stream().map(servicoMapper::toAsElementDTO).collect(Collectors.toList());
  }

  public Long getAllQuantidade(String nome) {
    if (nome != null && !nome.isBlank())
      return servicoRepository.findAllByNomeQuantidade(nome);
    else
      return servicoRepository.count();
  }

  public ServicoDto update(@NotNull @Positive Long id, @Valid @NotNull NovoServicoDto novoServicoDto) {
    var servico = servicoRepository.findByNome(novoServicoDto.getNome());

    if (servico != null && !servico.getId().equals(id))
      throw new NomeJaRegistradoException("Já existe um serviço com esse nome");

    return servicoRepository.findById(id)
        .map(servicoFound -> {
          servicoFound.setNome(novoServicoDto.getNome());
          servicoFound.setCategoria(categoriaRepository.findById(novoServicoDto.getCategoriaId()).get());
          return servicoMapper.toDTO(servicoRepository.save(servicoFound));
        }).orElseThrow();
  }

  public void delete(@PathVariable @NotNull @Positive Long id) {
    servicoRepository.delete(servicoRepository.findById(id).orElseThrow());
  }
}
