package br.dev.rodrigocury.loja.DAO;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.dev.rodrigocury.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto p){
		this.em.persist(p);
	}
	
	public Produto atualizar(Produto p) {
		return this.em.merge(p);
	}
	
	public void deleta(Produto p) {
		p = this.em.merge(p);
		this.em.remove(p);
	}
	
	public Produto pegaPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> buscarPorNome(String nome) {
		return this.em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class )
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		return this.em.createNamedQuery("produtosPorCategoria", Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> pegaTodos(){
		return this.em.createQuery("SELECT p FROM Produto p", Produto.class ).getResultList();
	}
	
	public BigDecimal buscarPrecoDoProduto(String nome) {

		return this.em.createQuery("SELECT p.preco FROM Produto p WHERE p.nome = :nome", BigDecimal.class )
				.setParameter("nome", nome)
				.getSingleResult();
	}

}
