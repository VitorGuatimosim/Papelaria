/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.service;

import com.papelariaTesourinha.sistemaWebPapelaria.data.VendaEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.VendaRepository;
import com.papelariaTesourinha.sistemaWebPapelaria.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vitor
 */
@Service
public class VendaService {
    @Autowired
    VendaRepository vendaRepository;
    
    public VendaEntity criarVenda(VendaEntity ven){
        ven.setId(null);
        
        vendaRepository.save(ven);
        
        return ven;
    }
    
    public VendaEntity getVendaId(Integer venId){
        return vendaRepository.findById(venId).orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada! ID:" + venId));
    }
    
    public List<VendaEntity> listarTodasVendas(){
        return vendaRepository.findAll();
    }
    
    public VendaEntity atualizarVenda(Integer venId, VendaEntity vendaRequest){
        VendaEntity ven = getVendaId(venId);
        
        ven.setValor_total(vendaRequest.getValor_total());
        ven.setData_venda(vendaRequest.getData_venda());
        ven.setHora_venda(vendaRequest.getHora_venda());
        ven.setForma_pagamento(vendaRequest.getForma_pagamento());
        ven.setNumero_parcelas(vendaRequest.getNumero_parcelas());
        ven.setId_usuario_fk(vendaRequest.getId_usuario_fk());
        
        vendaRepository.save(ven);
        return ven;
    }
    
    public void deletarVenda(Integer vendaId){
        VendaEntity ven = getVendaId(vendaId);
        
        vendaRepository.deleteById(ven.getId());
    }
}
