package br.dev.rodrigocury.loja.DAO;

import br.dev.rodrigocury.loja.modelo.Pedido;
import br.dev.rodrigocury.loja.modelo.Produto;
import br.dev.rodrigocury.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido p){
        this.em.persist(p);
    }

    public Pedido atualizar(Pedido p) {
        return this.em.merge(p);
    }

    public void deleta(Pedido p) {
        p = this.em.merge(p);
        this.em.remove(p);
    }

    public Pedido pegaPorId(Long id) {
        return this.em.find(Pedido.class, id);
    }

    public List<Pedido> pegaTodos(){
        return this.em.createQuery("SELECT p FROM Pedido p", Pedido.class ).getResultList();
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SElECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public Pedido buscarPedidoComCliente(Long id){
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioVendas (){
        String jpql = "SELECT new br.dev.rodrigocury.loja.vo.RelatorioDeVendasVo(" +
                "produto.nome, " +
                "SUM(itens.quantidade), " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens itens " +
                "JOIN itens.produto produto " +
                "GROUP BY itens.quantidade " +
                "ORDER BY itens.quantidade DESC";

        return em.createQuery(jpql, RelatorioDeVendasVo.class)
                .getResultList();

    }

}
