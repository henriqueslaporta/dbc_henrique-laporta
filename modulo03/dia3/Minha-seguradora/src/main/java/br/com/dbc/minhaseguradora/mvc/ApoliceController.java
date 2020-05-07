/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhaseguradora.mvc;

import br.com.dbc.minhaseguradora.entity.Apolice;
import br.com.dbc.minhaseguradora.service.ApoliceService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author henrique.laporta
 */
@Controller
public class ApoliceController {
    
    @Autowired
    private ApoliceService apoliceService;
    
    @RequestMapping("/")
    public String findAll(Model model) {
        List<Apolice> apolices = apoliceService.findAll(PageRequest.of(0, 20)).getContent();
        model.addAttribute("apolices", apolices);
        return "apolice";
    }
    
    @PostMapping("/")
    public String save(Apolice apolice, Model model) {

        apoliceService.save(apolice);
        List<Apolice> apolices = apoliceService.findAll(PageRequest.of(0, 20)).getContent();
        model.addAttribute("apolices", apolices);
        return "apolice";
    }
    
}
