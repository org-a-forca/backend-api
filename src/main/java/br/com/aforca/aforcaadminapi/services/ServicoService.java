package br.com.aforca.aforcaadminapi.services;

import br.com.aforca.aforcaadminapi.models.Servico;
import br.com.aforca.aforcaadminapi.repositories.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicoService {
  private final ServicoRepository servicoRepository;

  public Servico save(Servico servico) {
    return servicoRepository.save(servico);
  }

  public List<Servico> getAll() {
    return servicoRepository.findAll();
  }
}
