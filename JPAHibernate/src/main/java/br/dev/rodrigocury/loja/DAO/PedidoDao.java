package br.dev.rodrigocury.loja.DAO;

import br.dev.rodrigocury.loja.modelo.Pedido;
import br.dev.rodrigocury.loja.modelo.Produto;

import javax.persistence.EntityManager;
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

}
