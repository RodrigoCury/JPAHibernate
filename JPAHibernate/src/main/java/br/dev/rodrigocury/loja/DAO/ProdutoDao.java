package br.dev.rodrigocury.loja.DAO;

import javax.persistence.EntityManager;

import br.dev.rodrigocury.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto p){
		this.em.persist(p);
	}

}
