package br.com.aforca.aforcaadminapi.repositories;

import br.com.aforca.aforcaadminapi.models.CategoriaServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaServicoRepository extends JpaRepository<CategoriaServico, Long> {
    CategoriaServico findCategoriaServicoId(Long id);
}
