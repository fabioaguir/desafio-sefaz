package com.sefaz.desafio.views;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import com.sefaz.desafio.entities.Usuario;
import com.sefaz.desafio.services.UsuarioService;

@ViewScoped
@ManagedBean(name = "usuarioView")
public class UsuarioView {

	private Usuario usuario = new Usuario();
	
	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService service;

	public UsuarioView() {
	}

	public void salvar() {
		try {
			service.createUsuario(usuario);
			PrimeFaces.current().executeScript("PF('dlgFormUsuario').hide();");
			PrimeFaces.current().ajax().update("tableUsuario");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editar(Usuario usuario) {
		this.usuario = usuario;
		PrimeFaces.current().executeScript("PF('dlgFormUsuario').show();");
	}
	
	public void cadastrar() {
		this.usuario = new Usuario();
		PrimeFaces.current().executeScript("PF('dlgFormUsuario').show();");
	}

	public void deletar(Usuario usuario) {
		try {
			service.deleteUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {
		return service.listAll();
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
