package com.prueba.blaze.models.dao;

import java.util.List;
import com.prueba.blaze.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> getCliente();
	public void save(Cliente cliente);
	public Cliente buscarCliente(Long id);
	public void eliminar(Long id);
}
