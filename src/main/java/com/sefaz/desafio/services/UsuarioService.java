package com.sefaz.desafio.services;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.sefaz.desafio.dao.UsuarioDAO;
import com.sefaz.desafio.entities.Usuario;

@ManagedBean(name = "usuarioService")
@ApplicationScoped
public class UsuarioService {
    
	private UsuarioDAO dao = new UsuarioDAO();
	
    public void createUpdateUsuario(Usuario usuario) {
    	this.dao.save(usuario);
    }
    
    public void deleteUsuario(Usuario usuario) {
    	this.dao.delete(usuario);
    }
    
    public List<Usuario> listAll() {
    	return this.dao.listAll();
    }
}