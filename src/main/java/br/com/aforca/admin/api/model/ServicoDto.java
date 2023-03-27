package br.com.aforca.admin.api.model;

import br.com.aforca.admin.api.annotation.ServicoNomeUnico;
import br.com.aforca.admin.domain.entity.Categoria;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ServicoDto {
  private Long id;

  @NotEmpty(message = "O nome do serviço é obrigatório")
  @Size(max = 75, message = "O nome do serviço deve ter no máximo 75 caracteres")
  @ServicoNomeUnico
  private String nome;

  @NotNull(message = "A categoria do serviço é obrigatória")
  private Categoria categoria;
}
