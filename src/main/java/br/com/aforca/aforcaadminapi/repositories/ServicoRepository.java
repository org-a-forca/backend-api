package br.com.aforca.aforcaadminapi.repositories;

import br.com.aforca.aforcaadminapi.models.Servico;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ServicoRepository extends PagingAndSortingRepository<Servico, Long> {
  @Query(value = "select * from servico where nome like %:nome%", nativeQuery = true)
  List<Servico> findAllByNome(String nome, Pageable pageable);
}
