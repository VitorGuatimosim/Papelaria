/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vitor
 */
@RestController
@RequestMapping("/sessoes")
public class SessionController {
    @GetMapping("/le") 
    public String leSessao(HttpServletRequest request, Model model){ 
        HttpSession sessao = request.getSession(); 
        if(sessao != null){
            model.addAttribute("nome_usuario", (String)sessao.getAttribute("usuario"));
            return (String)sessao.getAttribute("usuario");
        } 
        return "redirect:/"; 
    }
    
    @GetMapping("/leCargo") 
    public String leSessaoCargo(HttpServletRequest request){ 
        HttpSession sessao = request.getSession(); 
        if(sessao != null){ 
            return (String)sessao.getAttribute("cargo"); 
        } 
        return "redirect:/"; 
    }
}
