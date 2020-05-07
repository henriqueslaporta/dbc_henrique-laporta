/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.laporta
 */
@Service
public class DateService {
    
    public LocalDateTime dateTimeNow(){
        return LocalDateTime.now();
    }
    
    public LocalDate dateNow(){
        return LocalDate.now();
    }
    
}
