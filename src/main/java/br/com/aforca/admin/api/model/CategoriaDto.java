package br.com.aforca.admin.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoriaDto {
  private Long id;

  @NotBlank(message = "O nome da categoria é obrigatório")
  @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres")
  private String nome;
}
