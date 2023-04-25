package br.com.aforca.admin.api.model;

import br.com.aforca.admin.api.annotation.ContratanteExistente;
import br.com.aforca.admin.api.annotation.TrabalhadorExistente;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class NovoContratoDto {
  @ContratanteExistente
  private Long contratanteId;

  @TrabalhadorExistente
  private Long trabalhadorId;

  @NotEmpty(message = "Os serviços contratados devem ser informados")
  private List<Long> servicosContratadosIds;

  @NotBlank(message = "O status é obrigatório")
  @Pattern(regexp = "^(Aberto|Desistiu|Feito|Para depois|Pegou fora)$", message = "O status deve ser Aberto, Desistiu, Feito, Para depois ou Pegou fora")
  private String status;

  @Min(value = 1, message = "A nota para o trabalhador deve ser maior ou igual a 1")
  @Max(value = 5, message = "A nota para o trabalhador deve ser menor ou igual a 5")
  private Integer notaTrabalhador;

  @Min(value = 1, message = "A nota para o contratante deve ser maior ou igual a 1")
  @Max(value = 5, message = "A nota para o contratante deve ser menor ou igual a 5")
  private Integer notaContratante;

  private String observacoes;
}
