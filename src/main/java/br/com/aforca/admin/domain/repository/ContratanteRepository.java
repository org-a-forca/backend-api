package br.com.aforca.admin.domain.repository;

import br.com.aforca.admin.domain.entity.Contratante;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContratanteRepository extends PagingAndSortingRepository<Contratante, Long> {
  @Query(value = "select * from contratante where nome ilike %:nome%", nativeQuery = true)
  List<Contratante> findAllByNome(String nome, Pageable pageable);

  @Query(value = "select * from contratante where telefone like %:telefone%", nativeQuery = true)
  List<Contratante> findAllByTelefone(String telefone, Pageable pageable);

  @Query(value = "select count(*) from contratante where nome ilike %:nome%", nativeQuery = true)
  Long findAllByNomeQuantidade(String nome);

  @Query(value = "select count(*) from contratante where telefone like %:telefone%", nativeQuery = true)
  Long findAllByTelefoneQuantidade(String telefone);
}
