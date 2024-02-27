package com.papelariaTesourinha.sistemaWebPapelaria.service;

import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoRepository;
import com.papelariaTesourinha.sistemaWebPapelaria.exception.ResourceNotFoundException;

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
        return produtoRepository.findById(prodId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado! ID:" + prodId));
    }
    
    public List<ProdutoEntity> listarTodosProdutos(){
        return produtoRepository.findAll();
    }
    
    public ProdutoEntity atualizarProduto(Integer prodId, ProdutoEntity produtoRequest){
        ProdutoEntity prod = getProdutoId(prodId);
        
        prod.setNome(produtoRequest.getNome());
        prod.setQuantidade(produtoRequest.getQuantidade());
        prod.setValor(produtoRequest.getValor());
        prod.setCategoria(produtoRequest.getCategoria());
        
        produtoRepository.save(prod);
        return prod;
    }
    
    public void deletarProduto(Integer prodId){
        ProdutoEntity prod = getProdutoId(prodId);
        
        produtoRepository.deleteById(prod.getId());
    }
}
