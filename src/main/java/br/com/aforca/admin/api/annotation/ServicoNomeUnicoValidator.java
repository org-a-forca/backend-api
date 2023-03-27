package br.com.aforca.admin.api.annotation;

import br.com.aforca.admin.domain.repository.ServicoRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class ServicoNomeUnicoValidator implements ConstraintValidator<ServicoNomeUnico, String> {
  private ServicoRepository servicoRepository;

  @Override
  public void initialize(ServicoNomeUnico constraintAnnotation) {}

  @Override
  public boolean isValid(String nome, ConstraintValidatorContext context) {
    return servicoRepository.findByNome(nome) == null;
  }
}
