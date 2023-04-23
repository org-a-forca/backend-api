package br.com.aforca.admin.api.model;

import br.com.aforca.admin.api.annotation.ServicoExistente;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class NovoTrabalhadorDto {
  @NotBlank(message = "O nome do trabalhador é obrigatório")
  @Size(max = 100, message = "O nome do trabalhador deve ter no máximo 100 caracteres")
  private String nome;

  @NotBlank(message = "O telefone do trabalhador é obrigatório")
  @Pattern(regexp = "[0-9]{11}", message = "O número do telefone do trabalhador deve ter somente os 11 dígitos")
  private String telefone;

  @Size(max = 200, message = "O endereço do trabalhador deve ter no máximo 200 caracteres")
  private String endereco;

  @Size(max = 100, message = "O email do trabalhador deve ter no máximo 100 caracteres")
  @Email(message = "O email do trabalhador deve ser de formato válido")
  private String email;

  private String referencias;
  private String restricoes;

  @ServicoExistente
  private List<Long> servicosIds;

  private String observacoes;
}
