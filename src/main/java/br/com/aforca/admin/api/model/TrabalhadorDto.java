package br.com.aforca.admin.api.model;

import br.com.aforca.admin.domain.entity.Servico;
import br.com.aforca.admin.domain.entity.Trabalhador;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TrabalhadorDto {
  private Long id;
  private String nome;
  private String telefone;
  private String endereco;
  private String email;
  private Integer nivel;
  private LocalDate dataCadastro;
  private LocalDate dataUltimoContrato;
  private String referencias;
  private String restricoes;
  private String observacoes;
  private List<ServicoDto> servicos;

  public TrabalhadorDto(Trabalhador trabalhador) {
    this.id = trabalhador.getId();
    this.nome = trabalhador.getNome();
    this.telefone = trabalhador.getTelefone();
    this.endereco = trabalhador.getEndereco();
    this.email = trabalhador.getEmail();
    this.nivel = trabalhador.getNivel();
    this.dataCadastro = trabalhador.getDataCadastro();
    this.dataUltimoContrato = trabalhador.getDataUltimoContrato();
    this.referencias = trabalhador.getReferencias();
    this.restricoes = trabalhador.getRestricoes();
    this.observacoes = trabalhador.getObservacoes();

    List<ServicoDto> servicos = new ArrayList<>();
    for (Servico servico : trabalhador.getServicos()) {
      servicos.add(new ServicoDto(servico));
    }

    this.servicos = servicos;
  }
}
