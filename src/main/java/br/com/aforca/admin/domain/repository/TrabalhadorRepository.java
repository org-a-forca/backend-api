package br.com.aforca.admin.domain.repository;

import br.com.aforca.admin.domain.entity.Trabalhador;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TrabalhadorRepository extends PagingAndSortingRepository<Trabalhador, Long> {
  @Query(value = "select * from trabalhador where nome ilike %:nome%", nativeQuery = true)
  List<Trabalhador> findAllByNome(String nome, Pageable pageable);

  @Query(value = "select count(*) from trabalhador where nome ilike %:nome%", nativeQuery = true)
  Long findAllByNomeQuantidade(String nome);

  @Query(value = "select distinct t.* from trabalhador t inner join trabalhadores_servicos ts on ts.trabalhador_id = t.id where ts.servico_id in :ids", nativeQuery = true)
  List<Trabalhador> findAllByIdsServicos(List<Long> ids, Pageable pageable);
}
