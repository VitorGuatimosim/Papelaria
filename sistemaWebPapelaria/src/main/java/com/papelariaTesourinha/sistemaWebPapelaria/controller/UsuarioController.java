package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import com.papelariaTesourinha.sistemaWebPapelaria.data.UsuarioEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vitor
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/adicionar")
    public ResponseEntity<UsuarioEntity> addUsuario(@Valid @RequestBody UsuarioEntity user){
        var novoUsuario = usuarioService.criarUsuario(user);
        
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllUsuarios(){
        List<UsuarioEntity> usuarios = usuarioService.listarTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public  ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Integer id){
        UsuarioEntity usuario = usuarioService.getUsuarioId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioEntity usuario){
        var usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
        
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Integer id){
        usuarioService.deletarUsuario(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
