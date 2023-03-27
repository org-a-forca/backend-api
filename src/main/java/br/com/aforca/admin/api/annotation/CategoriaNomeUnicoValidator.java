package br.com.aforca.admin.api.annotation;

import br.com.aforca.admin.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class CategoriaNomeUnicoValidator implements ConstraintValidator<CategoriaNomeUnico, String> {
  private CategoriaRepository categoriaRepository;

  @Override
  public void initialize(CategoriaNomeUnico constraintAnnotation) {}

  @Override
  public boolean isValid(String nome, ConstraintValidatorContext context) {
    return categoriaRepository.findByNome(nome) == null;
  }
}
