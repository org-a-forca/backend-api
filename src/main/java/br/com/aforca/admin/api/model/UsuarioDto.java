package br.com.aforca.admin.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioDto {
  @NotBlank(message = "O email do usuário é obrigatório")
  @Email(message = "O email do usuário deve ser de formato válido")
  private String email;

  @NotBlank(message = "A senha do usuário é obrigatório")
  private String senha;
}
