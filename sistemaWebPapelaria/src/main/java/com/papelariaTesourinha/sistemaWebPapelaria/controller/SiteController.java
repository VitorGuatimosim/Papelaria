/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import com.papelariaTesourinha.sistemaWebPapelaria.data.ProdutoEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.UsuarioEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.VendaEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.service.ProdutoService;
import com.papelariaTesourinha.sistemaWebPapelaria.service.UsuarioService;
import com.papelariaTesourinha.sistemaWebPapelaria.service.VendaService;
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
    @Autowired
    VendaService vendaService;
    
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
    public String cadastroVenda(Model model){
        VendaEntity ven = new VendaEntity();
        
        model.addAttribute("venda", ven);
        
        return "cadastroVenda";
    }
    
    @PostMapping("/salvarVenda")
    public String salvarVenda(@Valid @ModelAttribute("venda") VendaEntity ven, BindingResult result){
        if(ven.getId()== null){
            if(result.hasErrors()){
                return "cadastroVenda";
            }else{
                vendaService.criarVenda(ven);
            }
        }else{
            if(result.hasErrors()){
                return "editarVenda";
            }else{
                vendaService.atualizarVenda(ven.getId(), ven);
            }
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/controleVenda")
    public String controleVenda(Model model){
        model.addAttribute("listarVendas", vendaService.listarTodasVendas());
        return "controleVenda";
    }
    
    @GetMapping("/editarVenda/{id}")
    public String editarVenda(@PathVariable(value="id") Integer id, Model model){
        VendaEntity ven = vendaService.getVendaId(id);
        model.addAttribute("venda", ven);
        return "editarVenda";
    }
    
    @GetMapping("/deletarVenda/{id}")
    public String deletarVenda(@PathVariable(value="id") Integer id){
        vendaService.deletarVenda(id);
        return "redirect:/";
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
