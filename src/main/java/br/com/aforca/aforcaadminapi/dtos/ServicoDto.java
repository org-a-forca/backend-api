package br.com.aforca.aforcaadminapi.dtos;

import br.com.aforca.aforcaadminapi.models.Categoria;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ServicoDto {
  private Long id;

  @NotEmpty(message = "O nome do serviço é obrigatório")
  @Size(max = 75, message = "O nome do serviço deve ter no máximo 75 caracteres")
  private String nome;

  @NotNull(message = "A categoria do serviço é obrigatória")
  private Categoria categoria;
}
