package com.papelariaTesourinha.sistemaWebPapelaria.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_venda;
    
    @NotNull(message="Hora da venda obrigatória")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora_venda;
    
    @NotBlank(message="Forma de Pagamento obrigatória")
    private String forma_pagamento;
    
    @NotNull(message="Número de parcelas obrigatório")
    private Integer numero_parcelas;
    
    @NotNull(message="Usuário obrigatório")
    private Integer id_usuario_fk;
}