package br.dev.rodrigocury.loja.main;

import br.dev.rodrigocury.loja.DAO.CategoriaDao;
import br.dev.rodrigocury.loja.DAO.ClienteDao;
import br.dev.rodrigocury.loja.DAO.PedidoDao;
import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.modelo.*;
import br.dev.rodrigocury.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Cadastrador {
    public static void populaBD(){
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("Xiaomi", "Redmi 8", new BigDecimal("800"), celulares );
        Produto celular2 = new Produto("Samsung", "Redmi 8", new BigDecimal("800"), celulares );
        Produto celular3 = new Produto("Apple", "Redmi 8", new BigDecimal("80000"), celulares );
        Cliente cliente = new Cliente("Rodrigo Cury", "111.222.333-45");
        EntityManager em = JPAUtil.getEntity();

        ProdutoDao dao = new ProdutoDao(em);
        CategoriaDao cDao = new CategoriaDao(em);
        ClienteDao clDao = new ClienteDao(em);

        em.getTransaction().begin();
        cDao.cadastrar(celulares);
        dao.cadastrar(celular);
        dao.cadastrar(celular2);
        dao.cadastrar(celular3);
        clDao.cadastrar(cliente);

        Pedido pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(12, celular, pedido));
        pedido.addItem(new ItemPedido(32, celular2, pedido));
        pedido.addItem(new ItemPedido(42, celular3, pedido));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

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
//		Remove do BD
//		em.remove(celular);
//
//		em.flush();
//
//		// Fecha o Manager

//		System.out.println(em.find(Produto.class, 1l));
        System.out.println(pedido.getId());
        em.getTransaction().commit();
        em.close();

        // Detached | n fará o Update
//		celular.setNome("Rodrigo");
    }
}
