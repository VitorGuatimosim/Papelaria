package com.papelariaTesourinha.sistemaWebPapelaria.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private float valor_total;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_venda;
    
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora_venda;
    
    private String forma_pagamento;
    private Integer numero_parcelas;
    private Integer id_usuario_fk;
}
