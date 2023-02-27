package br.com.aforca.aforcaadminapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class CategoriaServicoDto {
  @Getter
  @Setter
  private Long id;

  @NotEmpty
  @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres")
  @Getter
  @Setter
  private String nome;
}
