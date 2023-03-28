package br.com.aforca.admin.api.model;

import lombok.Data;

@Data
public class ServicoDto {
  private Long id;
  private String nome;
  private CategoriaDto categoria;
}
