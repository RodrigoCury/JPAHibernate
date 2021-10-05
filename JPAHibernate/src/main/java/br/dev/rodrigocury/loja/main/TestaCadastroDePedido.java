package br.dev.rodrigocury.loja.main;

import br.dev.rodrigocury.loja.DAO.ClienteDao;
import br.dev.rodrigocury.loja.DAO.PedidoDao;
import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.modelo.*;
import br.dev.rodrigocury.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class TestaCadastroDePedido {

    public static void main(String[] args) {
        Cadastrador.cadastraProduto();

        EntityManager em = JPAUtil.getEntity();
        ProdutoDao produtoDao = new ProdutoDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        // Start Transaction
        em.getTransaction().begin();

        Produto xiaomi = produtoDao.pegaPorId(1L);
        Cliente cliente = clienteDao.pegaPorId(1L);

        Pedido pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(1, xiaomi, pedido));
        pedido.addItem(new ItemPedido(1, xiaomi, pedido));

        pedidoDao.cadastrar(pedido);


        em.getTransaction().commit();
        // Finaliza Transação
        em.close();
    }

}
