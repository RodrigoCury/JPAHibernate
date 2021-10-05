package br.dev.rodrigocury.loja.main;

import br.dev.rodrigocury.loja.DAO.PedidoDao;
import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.modelo.Pedido;
import br.dev.rodrigocury.loja.modelo.Produto;
import br.dev.rodrigocury.loja.util.JPAUtil;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;

public class TestaPerformance {

    public static void main(String[] args) {
        Cadastrador.populaBD();
        EntityManager em = JPAUtil.getEntity();
        PedidoDao pedidoDao= new PedidoDao(em);

        System.out.println(em.find(Produto.class, 1L));

        System.out.println(pedidoDao.buscarPedidoComCliente(5L));


    }
}
