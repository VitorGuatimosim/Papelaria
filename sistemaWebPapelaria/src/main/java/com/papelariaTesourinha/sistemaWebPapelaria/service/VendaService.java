/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.papelariaTesourinha.sistemaWebPapelaria.service;

import com.papelariaTesourinha.sistemaWebPapelaria.data.VendaEntity;
import com.papelariaTesourinha.sistemaWebPapelaria.data.VendaRepository;
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
        return vendaRepository.findById(venId).orElse(null);
    }
    
    public List<VendaEntity> listarTodasVendas(){
        return vendaRepository.findAll();
    }
}
