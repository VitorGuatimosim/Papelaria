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
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF; 

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
    
    @NotBlank(message="Nome obrigatório")
    private String nome;
    
    @NotBlank(message="CPF obrigatório")
    @CPF(message="CPF inválido")    
    private String cpf;
    
    @NotNull(message="Data de Nascimento obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;
    
    @NotNull(message="Data de Admissão obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String data_admissao;
        
    @NotBlank(message="Celular obrigatório")
    private String celular;
    
    @NotNull(message="Salário obrigatório")
    private float salario;
    
    @NotBlank(message="Cargo obrigatório")
    private String cargo;
    
    @NotBlank(message="Usuário obrigatório")
    private String usuario_nome;
    
    @NotBlank(message="Senha obrigatória")
    private String senha;
}