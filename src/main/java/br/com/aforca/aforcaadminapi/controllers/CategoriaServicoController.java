package br.com.aforca.aforcaadminapi.controllers;

import br.com.aforca.aforcaadminapi.dtos.CategoriaServicoDto;
import br.com.aforca.aforcaadminapi.models.CategoriaServico;
import br.com.aforca.aforcaadminapi.services.CategoriaServicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoriaservico")
@AllArgsConstructor
public class CategoriaServicoController {
    private final CategoriaServicoService categoriaServicoService;

    @PostMapping()
    public ResponseEntity<CategoriaServicoDto> create(@Valid @RequestBody CategoriaServicoDto categoriaServicoDto) {
        var categoriaServico = new CategoriaServico();
        categoriaServico.setNome(categoriaServicoDto.getNome());

        var savedCategoriaServico = categoriaServicoService.save(categoriaServico);

        var categoriaServicoResponse = new CategoriaServicoDto();
        categoriaServicoResponse.setId(savedCategoriaServico.getId());
        categoriaServicoResponse.setNome(savedCategoriaServico.getNome());

        return new ResponseEntity<>(categoriaServicoResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CategoriaServico>> getAll() {
        return new ResponseEntity<>(categoriaServicoService.getAll(), HttpStatus.OK);
    }
}
