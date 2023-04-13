package br.com.aforca.admin.api.annotation;

import br.com.aforca.admin.domain.repository.ServicoRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@AllArgsConstructor
public class ServicoExistenteValidator implements ConstraintValidator<ServicoExistente, List<Long>> {
  private ServicoRepository servicoRepository;

  @Override
  public void initialize(ServicoExistente constraintAnnotation) {}

  @Override
  public boolean isValid(List<Long> ids, ConstraintValidatorContext context) {
    if (ids == null) return false;

    if (ids.isEmpty()) return false;

    for (Long id : ids) {
      if (!servicoRepository.findById(id).isPresent()) return false;
    }

    return true;
  }
}
