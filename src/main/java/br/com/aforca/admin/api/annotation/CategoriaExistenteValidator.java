package br.com.aforca.admin.api.annotation;

import br.com.aforca.admin.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class CategoriaExistenteValidator implements ConstraintValidator<CategoriaExistente, Long> {
  private CategoriaRepository categoriaRepository;

  @Override
  public void initialize(CategoriaExistente constraintAnnotation) {}

  @Override
  public boolean isValid(Long id, ConstraintValidatorContext context) {
    return id != null && categoriaRepository.findById(id).isPresent();
  }
}
