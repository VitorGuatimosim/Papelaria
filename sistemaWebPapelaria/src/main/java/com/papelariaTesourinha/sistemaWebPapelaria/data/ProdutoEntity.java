package com.papelariaTesourinha.sistemaWebPapelaria.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author vitor
 */
@Data
@Entity
@Table(name="produto")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotBlank(message="Nome obrigatório")
    private String nome;
    
    @NotNull(message="Quantidade obrigatória")
    private Integer quantidade;
    
    @NotNull(message="Valor obrigatório")
    private float valor;
    
    @NotBlank(message="Categoria obrigatória")
    private String categoria;
}
