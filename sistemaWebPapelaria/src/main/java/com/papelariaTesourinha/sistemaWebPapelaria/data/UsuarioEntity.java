package com.papelariaTesourinha.sistemaWebPapelaria.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author vitor
 */
@Data
@Entity
@Table(name="usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String cpf;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String data_admissao;
    
    private String celular;
    private float salario;
    private String cargo;
    private String usuario;
    private String senha;
}