package br.com.aforca.aforcaadminapi.services;

import br.com.aforca.aforcaadminapi.models.CategoriaServico;
import br.com.aforca.aforcaadminapi.repositories.CategoriaServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaServicoService {
    private final CategoriaServicoRepository categoriaServicoRepository;

    public void saveCategoriaServico(CategoriaServico categoriaServico) {
        categoriaServicoRepository.save(categoriaServico);
    }

    public List<CategoriaServico> getAllCategoriaServico() {
        return categoriaServicoRepository.findAll();
    }

    public void deteleCategoriaServico(CategoriaServico categoriaServico) {
        categoriaServicoRepository.delete(categoriaServico);
    }
}
