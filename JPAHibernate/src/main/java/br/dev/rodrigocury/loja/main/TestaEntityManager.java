package br.dev.rodrigocury.loja.main;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.dev.rodrigocury.loja.DAO.CategoriaDao;
import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.modelo.Categoria;
import br.dev.rodrigocury.loja.modelo.Produto;
import br.dev.rodrigocury.loja.util.JPAUtil;

public class TestaEntityManager {

	public static void main(String[] args) {
		cadastraProduto();
		EntityManager em = JPAUtil.getEntity();
		ProdutoDao pDao = new ProdutoDao(em);
		
//		Produto p = pDao.pegaPorId(1L);
//		
//		System.out.println(p);
		
		pDao.buscarPorNomeDaCategoria("Celulares").forEach(System.out::println);
		
		System.out.println(pDao.buscarPrecoDoProduto("Apple"));
		
	}

	private static void cadastraProduto() {
		Categoria celulares = new Categoria("Celulares");
		Produto celular = new Produto("Xiaomi", "Redmi 8", new BigDecimal("800"), celulares );
		Produto celular2 = new Produto("Samsung", "Redmi 8", new BigDecimal("800"), celulares );
		Produto celular3 = new Produto("Apple", "Redmi 8", new BigDecimal("80000"), celulares );
		
		EntityManager em = JPAUtil.getEntity();
		
		ProdutoDao dao = new ProdutoDao(em);
		CategoriaDao cDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		cDao.cadastrar(celulares);
		dao.cadastrar(celular);
		dao.cadastrar(celular2);
		dao.cadastrar(celular3);
		
		// Estado managed | Fará o UPDATE
		celular.setNome("Xiaomi Redmi 8");
		celular.setDescricao("Custo-benefício");
		
//		// Salva no BD
		em.flush();
//		
//		// Remove os estados managed
		em.clear();

//		celular.setNome("Johnny");
//
//		// Devolve o Objeto em estado Managed
//		celular = em.merge(celular);
//
////		Remove do BD
////		em.remove(celular);
//
//		em.flush();
//
//		// Fecha o Manager

//		System.out.println(em.find(Produto.class, 1l));

		em.getTransaction().commit();
		em.close();
		
		// Detached | n fará o Update
//		celular.setNome("Rodrigo");
	}

}
