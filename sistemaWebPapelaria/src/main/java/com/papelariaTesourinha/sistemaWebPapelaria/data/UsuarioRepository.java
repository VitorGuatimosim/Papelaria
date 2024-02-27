package com.papelariaTesourinha.sistemaWebPapelaria.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vitor
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    
}
