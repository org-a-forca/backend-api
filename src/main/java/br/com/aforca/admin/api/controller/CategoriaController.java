package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.model.CategoriaDto;
import br.com.aforca.admin.api.model.NovaCategoriaDto;
import br.com.aforca.admin.domain.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public CategoriaDto create(@RequestBody @Valid @NotNull NovaCategoriaDto novaCategoriaDto) {
        return categoriaService.create(novaCategoriaDto);
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
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        categoriaService.detele(id);
    }
}
