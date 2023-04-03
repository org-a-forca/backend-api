package br.com.aforca.admin.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NovoContratanteDto {
  @NotBlank(message = "O nome do contratante é obrigatório")
  @Size(max = 100, message = "O nome do contratante deve ter no máximo 100 caracteres")
  private String nome;

  @NotBlank(message = "O telefone do contratante é obrigatório")
  @Pattern(regexp = "[0-9]{11}", message = "O número do telefone do contratante deve ter somente os 11 dígitos")
  private String telefone;

  @Size(max = 200, message = "O endereço do contratante deve ter no máximo 200 caracteres")
  private String endereco;

  @Size(max = 100, message = "O email do contratante deve ter no máximo 100 caracteres")
  private String email;
  private String observacoes;
}
