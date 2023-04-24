package br.com.aforca.admin.api.annotation;

import br.com.aforca.admin.domain.repository.TrabalhadorRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class TrabalhadorExistenteValidator implements ConstraintValidator<TrabalhadorExistente, Long> {
  private TrabalhadorRepository trabalhadorRepository;

  @Override
  public void initialize(TrabalhadorExistente constraintAnnotation) {}

  @Override
  public boolean isValid(Long id, ConstraintValidatorContext context) {
    return id != null && trabalhadorRepository.findById(id).isPresent();
  }
}
