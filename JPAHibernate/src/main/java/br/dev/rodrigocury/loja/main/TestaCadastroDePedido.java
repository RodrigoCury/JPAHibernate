package br.dev.rodrigocury.loja.main;

import br.dev.rodrigocury.loja.DAO.ClienteDao;
import br.dev.rodrigocury.loja.DAO.PedidoDao;
import br.dev.rodrigocury.loja.DAO.ProdutoDao;
import br.dev.rodrigocury.loja.modelo.*;
import br.dev.rodrigocury.loja.util.JPAUtil;
import br.dev.rodrigocury.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestaCadastroDePedido {

    public static void main(String[] args) {
        Cadastrador.populaBD();

        EntityManager em = JPAUtil.getEntity();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        // Start Transaction
        em.getTransaction().begin();

        Produto xiaomi = produtoDao.pegaPorId(1L);
        Produto samsung = produtoDao.pegaPorId(2L);
        Produto apple = produtoDao.pegaPorId(3L);
        Cliente cliente = clienteDao.pegaPorId(1L);

        Pedido pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(12, xiaomi, pedido));
        pedido.addItem(new ItemPedido(32, samsung, pedido));
        pedido.addItem(new ItemPedido(42, apple, pedido));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);


        em.getTransaction().commit();

        BigDecimal totalVendido = new PedidoDao(em).valorTotalVendido();
        System.out.println("total vendido: " + totalVendido);

        List<Produto> pPorCategoria = produtoDao.buscarPorNomeDaCategoria("Celulares");
        pPorCategoria.forEach(System.out::println);

        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioVendas();
        for (RelatorioDeVendasVo obj: relatorio) {
            System.out.println(obj);
        }

        // Finaliza Transação
        em.close();
    }

}
