package br.com.aforca.admin.domain.repository;

import br.com.aforca.admin.domain.entity.Contratante;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContratanteRepository extends PagingAndSortingRepository<Contratante, Long> {
}
