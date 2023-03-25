package br.com.aforca.admin.domain.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Column(name = "nome", length = 50, nullable = false, unique = true)
    @Getter
    @Setter
    private String nome;
}
