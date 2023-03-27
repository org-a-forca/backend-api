package br.com.aforca.admin.api.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoriaExistenteValidator.class)
public @interface CategoriaExistente {
  String message() default "A categoria é obrigatória e deve estar registrada";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
