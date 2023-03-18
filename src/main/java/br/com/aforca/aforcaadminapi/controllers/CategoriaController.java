package br.com.aforca.aforcaadminapi.controllers;

import br.com.aforca.aforcaadminapi.dtos.CategoriaDto;
import br.com.aforca.aforcaadminapi.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CategoriaDto create(@RequestBody @Valid @NotNull CategoriaDto categoriaDto) {
        return categoriaService.create(categoriaDto);
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
