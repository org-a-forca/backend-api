package br.com.aforca.admin.api.model;

import br.com.aforca.admin.api.annotation.CategoriaExistente;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NovoServicoDto {
  @NotBlank(message = "O nome do serviço é obrigatório")
  @Size(max = 75, message = "O nome do serviço deve ter no máximo 75 caracteres")
  private String nome;

  @CategoriaExistente
  private Long categoriaId;
}
