package br.com.aforca.admin.api.model;

import br.com.aforca.admin.api.annotation.CategoriaExistente;
import br.com.aforca.admin.api.annotation.ServicoNomeUnico;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NovoServicoDto {
  @NotBlank(message = "O nome do serviço é obrigatório")
  @Size(max = 75, message = "O nome do serviço deve ter no máximo 75 caracteres")
  @ServicoNomeUnico
  private String nome;

  @CategoriaExistente
  private Long categoriaId;
}
