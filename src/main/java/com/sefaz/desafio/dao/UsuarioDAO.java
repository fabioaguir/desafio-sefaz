package com.sefaz.desafio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.sefaz.desafio.entities.Usuario;
import com.sefaz.desafio.entities.Telefone;
import com.sefaz.desafio.utils.PersistenceSingleton;

public class UsuarioDAO {

	public void save(Usuario usuario) {
		EntityManager entityManager = PersistenceSingleton.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Usuario usu = entityManager.merge(usuario);
		
		entityManager.createQuery("DELETE FROM Telefone WHERE usuario = " + usu.getId()).executeUpdate();

		usuario.getTelefones().forEach(item -> {
			item.setUsuario(usu);
			item.setId(0);
			entityManager.persist(item);
		});

		entityTransaction.commit();
		entityManager.close();
	}

	public List<Usuario> listAll() {
		EntityManager entityManager = PersistenceSingleton.getEntityManager();

		String hql = "FROM Usuario usu ORDER BY usu.nome";

		TypedQuery<Usuario> query = entityManager.createQuery(hql, Usuario.class);
		List<Usuario> list = query.getResultList();
		
		list.forEach(item -> {
			TypedQuery<Telefone> qry = entityManager.createQuery("FROM Telefone telefone WHERE telefone.usuario = " + item.getId(), Telefone.class);
			List<Telefone> telefones = qry.getResultList();
			item.setTelefones(telefones);
		});

		entityManager.close();
		return list;
	}

	public void delete(Usuario usuario) {
		EntityManager entityManager = PersistenceSingleton.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.createQuery("DELETE FROM Usuario WHERE id = " + usuario.getId()).executeUpdate();
		entityTransaction.commit();
		entityManager.close();
	}
}
