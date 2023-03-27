package br.com.aforca.admin.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.aforca.admin.domain.entity.Servico;

import java.util.List;

public interface ServicoRepository extends PagingAndSortingRepository<Servico, Long> {
  @Query(value = "select * from servico where nome like %:nome%", nativeQuery = true)
  List<Servico> findAllByNome(String nome, Pageable pageable);

  @Query(value = "select * from servico where lower(nome) = lower(:nome)", nativeQuery = true)
  Servico findByNome(String nome);
}
