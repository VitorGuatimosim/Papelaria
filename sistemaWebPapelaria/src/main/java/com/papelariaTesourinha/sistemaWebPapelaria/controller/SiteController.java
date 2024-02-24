/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author vitor
 */

@Controller
public class SiteController {
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
    public String cadastroProduto(){
        return "cadastroProduto";
    }
    
    @GetMapping("/controleVenda")
    public String controleVenda(){
        return "controleVenda";
    }
    
    @GetMapping("/controleProduto")
    public String controleProduto(){
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
    
    @GetMapping("/editarProduto")
    public String editarProduto(){
        return "editarProduto";
    }
}
