package br.com.aforca.admin.domain.repository;

import br.com.aforca.admin.domain.entity.Trabalhador;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrabalhadorRepository extends PagingAndSortingRepository<Trabalhador, Long> {
}
