package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.exception.NomeCategoriaJaRegistradoException;
import br.com.aforca.admin.api.model.CategoriaDto;
import br.com.aforca.admin.api.model.NovaCategoriaDto;
import br.com.aforca.admin.domain.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody @Valid @NotNull NovaCategoriaDto novaCategoriaDto) {
        try {
            return new ResponseEntity<>(categoriaService.create(novaCategoriaDto), HttpStatus.CREATED);
        } catch (NomeCategoriaJaRegistradoException ex) {
            Map<String, Object> corpoDaResposta = new HashMap<>();
            corpoDaResposta.put("nome", ex.getMessage());

            return new ResponseEntity<>(corpoDaResposta, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public CategoriaDto getById(@PathVariable @NotNull @Positive Long id) {
        return categoriaService.getById(id);
    }

    @GetMapping
    public @ResponseBody List<CategoriaDto> getAll() {
        return categoriaService.getAll();
    }

    @PutMapping("/{id}")
    public CategoriaDto update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CategoriaDto categoriaDto) {
        return categoriaService.update(id, categoriaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable @NotNull @Positive Long id) {
        try {
            categoriaService.detele(id);

            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException ex) {
            Map<String, Object> corpoDaResposta = new HashMap<>();
            corpoDaResposta.put("mensagem", "Essa categoria está sendo utilizada em outros registros");

            return new ResponseEntity<>(corpoDaResposta, HttpStatus.CONFLICT);
        }
    }
}
