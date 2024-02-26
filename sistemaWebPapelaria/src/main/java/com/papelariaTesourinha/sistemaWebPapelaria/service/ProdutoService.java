package com.papelariaTesourinha.sistemaWebPapelaria.service;

import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vitor
 */
@Service
public class ProdutoService {
    
    @Autowired
    ProdutoRepository produtoRepository;
    
    public ProdutoEntity criarProduto(ProdutoEntity prod){
        prod.setId(null);
        
        produtoRepository.save(prod);
        
        return prod;
    }
    
    public ProdutoEntity getProdutoId(Integer prodId){
        return produtoRepository.findById(prodId).orElse(null);
    }
    
    public List<ProdutoEntity> listarTodosProdutos(){
        return produtoRepository.findAll();
    }
}
