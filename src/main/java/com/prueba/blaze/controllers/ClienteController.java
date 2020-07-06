package com.prueba.blaze.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prueba.blaze.models.dao.IClienteDao;
import com.prueba.blaze.models.entity.Cliente;

@Controller
public class ClienteController {

	@Autowired
	private IClienteDao clienteDao;
	
	@GetMapping(value="listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "List of Clients");
		model.addAttribute("clientes", clienteDao.getCliente());
		return "listar";
	}
	
	@GetMapping(value="/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente",cliente);
		model.addAttribute("titulo","Formulario de cliente");
		model.addAttribute("boton","Register Client");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(Cliente cliente) {
		clienteDao.save(cliente);
		return "redirect:/listar";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id,Map<String,Object> model) {
		Cliente cliente = null;
		if(id>0) {
			cliente = clienteDao.buscarCliente(id);
		}else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Edit Client");
		model.put("boton","Update Client");
		return "form";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			clienteDao.eliminar(id);
		}

		return "redirect:/listar";
	}
}
