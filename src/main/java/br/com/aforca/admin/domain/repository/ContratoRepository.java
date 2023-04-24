package br.com.aforca.admin.domain.repository;

import br.com.aforca.admin.domain.entity.Contrato;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContratoRepository extends PagingAndSortingRepository<Contrato, Long> {
}
