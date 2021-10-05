package br.dev.rodrigocury.loja.main;

import javax.persistence.EntityManager;

import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.util.JPAUtil;

public class TestaEntityManager {

	public static void main(String[] args) {
		Cadastrador.populaBD();
		EntityManager em = JPAUtil.getEntity();
		ProdutoDao pDao = new ProdutoDao(em);
		
//		Produto p = pDao.pegaPorId(1L);
//		
//		System.out.println(p);
		
		pDao.buscarPorNomeDaCategoria("Celulares").forEach(System.out::println);
		
		System.out.println(pDao.buscarPrecoDoProduto("Apple"));
		
	}

}
