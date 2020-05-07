/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.seguradora.mvc;

import br.com.dbc.seguradora.entity.Cliente;
import br.com.dbc.seguradora.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author tiago
 */
@Controller
@RequestMapping("/mvc/cliente")
public class ClienteMvcController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String list(Model model, WebRequest webRequest) {
        Integer page = Integer.valueOf(StringUtils.isEmpty(webRequest.getParameter("page")) ? "0" : webRequest.getParameter("page"));
        Integer size = Integer.valueOf(StringUtils.isEmpty(webRequest.getParameter("size")) ? "20" : webRequest.getParameter("size"));
        Page<Cliente> clientes = clienteService.findAll(PageRequest.of(page, size));
        model.addAttribute("clientes", clientes);

        return "listaClientes";
    }

    @RequestMapping(path = "/{cliente}", method = RequestMethod.GET)
    public String edit(@PathVariable("cliente") Long idCliente, Model model) {

        Cliente cliente = clienteService.findById(idCliente).orElse(null);
        model.addAttribute("cliente", cliente);

        return "editarCliente";
    }

    @RequestMapping(path = "/{cliente}", method = RequestMethod.POST)
    public String update(@PathVariable("cliente") Long idCliente, Cliente cliente) {

        cliente.setId(idCliente);

        clienteService.save(cliente);

        return "redirect:/mvc/cliente/";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(Cliente cliente) {

        cliente.setId(null);

        clienteService.save(cliente);

        return "redirect:/mvc/cliente/";
    }

}
