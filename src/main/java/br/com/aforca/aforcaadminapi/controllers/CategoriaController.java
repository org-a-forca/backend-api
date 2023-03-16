package br.com.aforca.aforcaadminapi.controllers;

import br.com.aforca.aforcaadminapi.dtos.CategoriaDto;
import br.com.aforca.aforcaadminapi.models.Categoria;
import br.com.aforca.aforcaadminapi.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping()
    public ResponseEntity<CategoriaDto> create(@Valid @RequestBody CategoriaDto categoriaDto) {
        var categoriaServico = new Categoria();
        categoriaServico.setNome(categoriaDto.getNome());

        var savedCategoriaServico = categoriaService.save(categoriaServico);

        var categoriaServicoResponse = new CategoriaDto();
        categoriaServicoResponse.setId(savedCategoriaServico.getId());
        categoriaServicoResponse.setNome(savedCategoriaServico.getNome());

        return new ResponseEntity<>(categoriaServicoResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Categoria>> getAll() {
        return new ResponseEntity<>(categoriaService.getAll(), HttpStatus.OK);
    }
}
