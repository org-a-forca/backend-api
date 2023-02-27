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

    public CategoriaServico save(CategoriaServico categoriaServico) {
        return categoriaServicoRepository.save(categoriaServico);
    }

    public List<CategoriaServico> getAll() {
        return categoriaServicoRepository.findAll();
    }

    public void detele(CategoriaServico categoriaServico) {
        categoriaServicoRepository.delete(categoriaServico);
    }
}
