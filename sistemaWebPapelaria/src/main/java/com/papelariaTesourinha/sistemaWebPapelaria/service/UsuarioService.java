package com.papelariaTesourinha.sistemaWebPapelaria.service;

import com.papelariaTesourinha.sistemaWebPapelaria.data.UsuarioEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vitor
 */
@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public UsuarioEntity criarUsuario(UsuarioEntity user){
        user.setId(null);
        
        usuarioRepository.save(user);
        
        return user;
    }
    
     public UsuarioEntity getUsuarioId(Integer userId){
        return usuarioRepository.findById(userId).orElse(null);
    }
    
    public List<UsuarioEntity> listarTodosUsuarios(){
        return usuarioRepository.findAll();
    }
}
