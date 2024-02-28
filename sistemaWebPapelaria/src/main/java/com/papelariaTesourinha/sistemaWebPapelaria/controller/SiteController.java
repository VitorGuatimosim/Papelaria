/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author vitor
 */

@Controller
public class SiteController {
    @Autowired
    ProdutoService produtoService;
    
    @GetMapping("/")
    public String viewHomePage(){
        return "home";
    }
    
    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }
    
    @GetMapping("/cadastroVenda")
    public String cadastroVenda(){
        return "cadastroVenda";
    }
    
    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(){
        return "cadastroUsuario";
    }
    
    @GetMapping("/cadastroProduto")
    public String cadastroProduto(Model model){
        ProdutoEntity prod = new ProdutoEntity();
        
        model.addAttribute("produto", prod);
        return "cadastroProduto";
    }
    
    @PostMapping("/salvarProduto")
    public String salvarProduto(@Valid @ModelAttribute("produto") ProdutoEntity prod, BindingResult result){
        
        
        if(prod.getId()== null){
            if(result.hasErrors()){
                return "cadastroProduto";
            }else{
                produtoService.criarProduto(prod);
            }
        }else{
            if(result.hasErrors()){
                return "editarProduto";
            }else{
                produtoService.atualizarProduto(prod.getId(), prod);
            }
        }
        
        return "redirect:/";
    }

    @GetMapping("/controleVenda")
    public String controleVenda(){
        return "controleVenda";
    }
    
    @GetMapping("/controleProduto")
    public String controleProduto(Model model){
        
        model.addAttribute("listarProdutos", produtoService.listarTodosProdutos());
        
        return "controleProduto";
    }
    
    @GetMapping("/controleUsuario")
    public String controleUsuario(){
        return "controleUsuario";
    }
    
    @GetMapping("/editarVenda")
    public String editarVenda(){
        return "editarVenda";
    }
    
    @GetMapping("/editarUsuario")
    public String editarUsuario(){
        return "editarUsuario";
    }
    
    @GetMapping("/editarProduto/{id}")
    public String editarProduto(@PathVariable(value="id") Integer id, Model model){
        ProdutoEntity prod = produtoService.getProdutoId(id);
        model.addAttribute("produto", prod);
        return "editarProduto";
    }
    
    @GetMapping("/deletarProduto/{id}")
    public String deletarProduto(@PathVariable(value="id") Integer id){
        produtoService.deletarProduto(id);
        return "redirect:/";
    }
}
