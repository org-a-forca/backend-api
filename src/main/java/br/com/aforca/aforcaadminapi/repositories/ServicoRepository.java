package br.com.aforca.aforcaadminapi.repositories;

import br.com.aforca.aforcaadminapi.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
  @Query(value = "select * from servico where nome like %:nome%", nativeQuery = true)
  List<Servico> findAllByNome(String nome);
}
