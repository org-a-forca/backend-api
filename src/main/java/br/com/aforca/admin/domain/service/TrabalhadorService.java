package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.mapper.TrabalhadorMapper;
import br.com.aforca.admin.domain.repository.TrabalhadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrabalhadorService {
  private final TrabalhadorRepository trabalhadorRepository;
  private final TrabalhadorMapper trabalhadorMapper;
}
