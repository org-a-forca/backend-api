package br.com.aforca.admin.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class NovoUsuarioDto {
  @NotBlank(message = "O email do usuário é obrigatório")
  @Size(max = 100, message = "O email do trabalhador deve ter no máximo 100 caracteres")
  @Email(message = "O email do usuário deve ser de formato válido")
  private String email;

  @NotBlank(message = "A senha do usuário é obrigatório")
  @Size(min = 6, message = "A senha do usuário deve ter no mínimo 6 caracteres")
  private String senha;
}
