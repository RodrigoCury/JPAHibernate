package br.dev.rodrigocury.loja.DAO;

import br.dev.rodrigocury.loja.modelo.Cliente;
import br.dev.rodrigocury.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
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

    public List<Cliente> pegaTodos() {
        return this.em.createQuery("SELECT p FROM Cliente p", Cliente.class).getResultList();
    }

    public List<Cliente> buscarClientes(String nome, String cpf) {
        String jpql = "SELECT c FROM Cliente c WHERE 1=1 ";
        if (nome != null && !nome.trim().isEmpty()) {
            jpql += "AND c.nome = :nome ";
        }
        if (cpf != null) {
            jpql += " AND c.cpf = :cpf ";
        }
        TypedQuery<Cliente> query = this.em.createQuery(jpql, Cliente.class);
        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (cpf != null) {
            query.setParameter("cpf", cpf);
        }
        return query.getResultList();
    }

}
