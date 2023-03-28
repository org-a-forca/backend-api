package br.com.aforca.admin.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServicoAsElementDto {
  private Long id;

  private String nome;

  @JsonProperty("categoria_nome")
  private String categoriaNome;
}
