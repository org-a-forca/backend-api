package br.com.aforca.admin.api.annotation;

import br.com.aforca.admin.domain.repository.ContratanteRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class ContratanteExistenteValidator implements ConstraintValidator<ContratanteExistente, Long> {
  private ContratanteRepository contratanteRepository;

  @Override
  public void initialize(ContratanteExistente constraintAnnotation) {}

  @Override
  public boolean isValid(Long id, ConstraintValidatorContext context) {
    return id != null && contratanteRepository.findById(id).isPresent();
  }
}
