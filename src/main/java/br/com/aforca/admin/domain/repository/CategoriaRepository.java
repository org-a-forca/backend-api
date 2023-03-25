package br.com.aforca.admin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aforca.admin.domain.entity.Categoria;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  @Query(value = "select * from categoria where lower(nome) = lower(:nome)", nativeQuery = true)
  Categoria findByNome(String nome);
}
