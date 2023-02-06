package br.com.aforca.aforcaadminapi.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categoria_servico")
public class CategoriaServico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Column(name = "nome", length = 50, nullable = false)
    @Getter
    @Setter
    private String nome;
}
