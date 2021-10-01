package br.dev.rodrigocury.loja.main;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.modelo.Produto;
import br.dev.rodrigocury.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto celular = new Produto("Xiaomi", "Redmi 8", new BigDecimal("800"));
		
		EntityManager em = JPAUtil.getEntity();
		
		ProdutoDao dao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
		
		
	}

}
