package br.com.aforca.aforcaadminapi.repositories;

import br.com.aforca.aforcaadminapi.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
