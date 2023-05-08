package br.com.aforca.admin.domain.repository;

import br.com.aforca.admin.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  @Query(value = "select * from usuario where email = :email")
  Optional<Usuario> findByEmail(String email);
}
