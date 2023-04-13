package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.domain.service.TrabalhadorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trabalhador")
@AllArgsConstructor
public class TrabalhadorController {
  private final TrabalhadorService trabalhadorService;
}
