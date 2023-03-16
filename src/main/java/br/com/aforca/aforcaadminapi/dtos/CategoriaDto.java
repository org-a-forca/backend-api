package br.com.aforca.aforcaadminapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CategoriaDto {
  private Long id;

  @NotEmpty
  @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres")
  private String nome;
}
