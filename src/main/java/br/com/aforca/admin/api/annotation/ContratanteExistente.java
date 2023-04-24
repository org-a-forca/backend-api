package br.com.aforca.admin.api.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContratanteExistenteValidator.class)
public @interface ContratanteExistente {
  String message() default "O contratante é obrigatório e deve estar registrado";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
