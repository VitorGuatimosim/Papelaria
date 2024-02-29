package com.papelariaTesourinha.sistemaWebPapelaria.service;

import com.papelariaTesourinha.sistemaWebPapelaria.data.UsuarioEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.UsuarioRepository;
import com.papelariaTesourinha.sistemaWebPapelaria.exception.ResourceNotFoundException;
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
        return usuarioRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado! ID:" + userId));
    }
    
    public List<UsuarioEntity> listarTodosUsuarios(){
        return usuarioRepository.findAll();
    }
    
     public UsuarioEntity atualizarUsuario(Integer userId, UsuarioEntity usuarioRequest){
        UsuarioEntity user = getUsuarioId(userId);
        
        user.setNome(usuarioRequest.getNome());
        user.setCpf(usuarioRequest.getCpf());
        user.setData_nascimento(usuarioRequest.getData_nascimento());
        user.setData_admissao(usuarioRequest.getData_admissao());
        user.setCelular(usuarioRequest.getCelular());
        user.setSalario(usuarioRequest.getSalario());
        user.setCargo(usuarioRequest.getCargo());
        user.setUsuario_nome(usuarioRequest.getUsuario_nome());
        user.setSenha(usuarioRequest.getSenha());
        
        usuarioRepository.save(user);
        return user;
    }
     
     public void deletarUsuario(Integer userId){
        UsuarioEntity user = getUsuarioId(userId);
        
        usuarioRepository.deleteById(user.getId());
    }
}