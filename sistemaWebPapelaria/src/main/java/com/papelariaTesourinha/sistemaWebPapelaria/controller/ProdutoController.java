package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.service.ProdutoService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;
    
    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoEntity> addProduto(@Valid @RequestBody ProdutoEntity prod){
        var novoProduto = produtoService.criarProduto(prod);
        
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllProdutos(){
        List<ProdutoEntity> produtos = produtoService.listarTodosProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public  ResponseEntity<ProdutoEntity> getProdutoById(@PathVariable Integer id){
        ProdutoEntity produto = produtoService.getProdutoId(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoEntity produto){
        var produtoAtualizado = produtoService.atualizarProduto(id, produto);
        
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarProduto(@PathVariable Integer id){
        produtoService.deletarProduto(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
