package br.com.aforca.aforcaadminapi.controllers;

import br.com.aforca.aforcaadminapi.models.CategoriaServico;
import br.com.aforca.aforcaadminapi.services.CategoriaServicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoriaServicoController {
    private final CategoriaServicoService categoriaServicoService;

    @PostMapping("/categoriaservico")
    public ResponseEntity<CategoriaServico> createCategoriaServico(@RequestBody CategoriaServico categoriaServico) {
        categoriaServicoService.saveCategoriaServico(categoriaServico);
        return new ResponseEntity<>(categoriaServico, HttpStatus.CREATED);
    }

    @GetMapping("/categoriaservico")
    public ResponseEntity<List<CategoriaServico>> getAllCategoriaServico() {
        return new ResponseEntity<>(categoriaServicoService.getAllCategoriaServico(), HttpStatus.OK);
    }
}
