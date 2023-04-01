package br.com.aforca.admin.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NovoContratanteDto {
  @NotBlank(message = "O nome do contratante é obrigatório")
  @Size(max = 100, message = "O nome não deve ultrapassar 100 caracteres")
  private String nome;

  @NotBlank(message = "O telefone do contratante é obrigatório")
  @Pattern(regexp = "[0-9]{11}", message = "Informe somente os 11 números do telefone do contratante")
  @Size(max = 11, message = "O número do telefone não deve ultrapassar 11 digitos")
  private String telefone;

  @Size(max = 200, message = "O endereço não deve ultrapassar 200 caracteres")
  private String endereco;

  @Size(max = 100, message = "O email não deve ultrapassar 100 caracteres")
  private String email;
  private String observacoes;
}
