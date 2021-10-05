package br.dev.rodrigocury.loja.DAO;

import br.dev.rodrigocury.loja.modelo.Cliente;
import br.dev.rodrigocury.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {
    private final EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente c){
        this.em.persist(c);
    }

    public Cliente atualizar(Cliente c) {
        return this.em.merge(c);
    }

    public void deleta(Cliente c) {
        c = this.em.merge(c);
        this.em.remove(c);
    }

    public Cliente pegaPorId(Long id) {
        return this.em.find(Cliente.class, id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return this.em.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome", Cliente.class )
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Cliente> pegaTodos(){
        return this.em.createQuery("SELECT p FROM Cliente p", Cliente.class ).getResultList();
    }

}
