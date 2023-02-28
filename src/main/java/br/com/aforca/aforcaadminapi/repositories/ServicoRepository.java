package br.com.aforca.aforcaadminapi.repositories;

import br.com.aforca.aforcaadminapi.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
