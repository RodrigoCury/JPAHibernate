package br.dev.rodrigocury.loja.main;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.dev.rodrigocury.loja.DAO.CategoriaDao;
import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.modelo.Categoria;
import br.dev.rodrigocury.loja.modelo.Produto;
import br.dev.rodrigocury.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("Celulares");
		Produto celular = new Produto("Xiaomi", "Redmi 8", new BigDecimal("800"), celulares );
		
		EntityManager em = JPAUtil.getEntity();
		
		ProdutoDao dao = new ProdutoDao(em);
		CategoriaDao cDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		cDao.cadastrar(celulares);
		dao.cadastrar(celular);
		em.getTransaction().commit();
		Produto p1 = em.find(Produto.class, 1L);
		System.out.println(p1.getNome() + p1.getDescricao());
		em.close();
		
		
	}

}
