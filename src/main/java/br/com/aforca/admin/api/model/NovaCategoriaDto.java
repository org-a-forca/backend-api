package br.com.aforca.admin.api.model;

import br.com.aforca.admin.api.annotation.CategoriaNomeUnico;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NovaCategoriaDto {
  @NotBlank(message = "O nome da categoria é obrigatório")
  @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres")
  @CategoriaNomeUnico
  private String nome;
}
