/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.controller;

import com.papelariaTesourinha.sistemaWebPapelaria.data.VendaEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.service.VendaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    VendaService vendaService;
    
    @PostMapping("/adicionar")
    public ResponseEntity<VendaEntity> addVenda(@RequestBody VendaEntity ven){
        var novaVenda = vendaService.criarVenda(ven);
        
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllVendas(){
        List<VendaEntity> vendas = vendaService.listarTodasVendas();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public  ResponseEntity<VendaEntity> getVendaById(@PathVariable Integer id){
        VendaEntity venda = vendaService.getVendaId(id);
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VendaEntity> atualizarVenda(@PathVariable Integer id, @RequestBody VendaEntity venda){
        var vendaAtualizada = vendaService.atualizarVenda(id, venda);
        
        return new ResponseEntity<>(vendaAtualizada, HttpStatus.OK);
    }
}
