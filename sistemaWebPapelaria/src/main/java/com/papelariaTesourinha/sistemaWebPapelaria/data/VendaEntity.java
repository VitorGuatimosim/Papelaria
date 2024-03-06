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
@Table(name="venda")
public class VendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull(message="Valor total obrigatório")
    private float valor_total;
    
    @NotNull(message="Data de venda obrigatória")
    private String data_venda;
    
    @NotNull(message="Hora da venda obrigatória")
    private String hora_venda;
    
    @NotBlank(message="Forma de Pagamento obrigatória")
    private String forma_pagamento;
    
    @NotNull(message="Número de parcelas obrigatório")
    private Integer numero_parcelas;
}