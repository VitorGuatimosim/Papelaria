/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.UsuarioEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.service.ProdutoService;
import com.papelariaTesourinha.sistemaWebPapelaria.service.UsuarioService;
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
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/")
    public String viewHomePage(){
        return "home";
    }
    
    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }
    
    /* CONTROLLER VENDA */
    
    @GetMapping("/cadastroVenda")
    public String cadastroVenda(){
        return "cadastroVenda";
    }
    
    @GetMapping("/controleVenda")
    public String controleVenda(){
        return "controleVenda";
    }
    
    @GetMapping("/editarVenda")
    public String editarVenda(){
        return "editarVenda";
    }
    
    /* CONTROLLER USUÁRIO*/
    
    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(Model model){
        UsuarioEntity user = new UsuarioEntity();
        
        model.addAttribute("usuario", user);
        
        return "cadastroUsuario";
    }
    
    @PostMapping("/salvarUsuario")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") UsuarioEntity user, BindingResult result){
         if(user.getId()== null){
            if(result.hasErrors()){
                return "cadastroUsuario";
            }else{
                usuarioService.criarUsuario(user);
            }
        }else{
            if(result.hasErrors()){
                return "editarUsuario";
            }else{
                usuarioService.atualizarUsuario(user.getId(), user);
            }
        }
        
        return "redirect:/";
    }
    
     @GetMapping("/controleUsuario")
    public String controleUsuario(Model model){
        
        model.addAttribute("listarUsuarios", usuarioService.listarTodosUsuarios());
        
        return "controleUsuario";
    }
    
    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable(value="id") Integer id, Model model){
        UsuarioEntity user = usuarioService.getUsuarioId(id);
        model.addAttribute("usuario", user);
        return "editarUsuario";
    }
    
    @GetMapping("/deletarUsuario/{id}")
    public String deletarUsuario(@PathVariable(value="id") Integer id){
        usuarioService.deletarUsuario(id);
        return "redirect:/";
    }
    
    /* CONTROLLER PRODUTO*/
    
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
    
    @GetMapping("/controleProduto")
    public String controleProduto(Model model){
        
        model.addAttribute("listarProdutos", produtoService.listarTodosProdutos());
        
        return "controleProduto";
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
