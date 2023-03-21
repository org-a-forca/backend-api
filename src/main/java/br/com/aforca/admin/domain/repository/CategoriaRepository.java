package br.com.aforca.admin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aforca.admin.domain.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
