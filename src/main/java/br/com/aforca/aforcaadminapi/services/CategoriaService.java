package br.com.aforca.aforcaadminapi.services;

import br.com.aforca.aforcaadminapi.models.Categoria;
import br.com.aforca.aforcaadminapi.repositories.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    public void detele(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}
