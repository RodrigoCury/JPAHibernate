package br.dev.rodrigocury.loja.DAO;

import javax.persistence.EntityManager;

import br.dev.rodrigocury.loja.modelo.Categoria;

public class CategoriaDao {
	
	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria c){
		this.em.persist(c);
	}

}
