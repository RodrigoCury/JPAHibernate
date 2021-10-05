package br.dev.rodrigocury.loja.DAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	public List<Produto> buscaPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro){
		String sql ="SELECT p FROM Produto p WHERE 1=1 ";
		if(nome != null && !nome.trim().isEmpty()) {
			sql += "AND p.nome = :nome ";
		}
		if(preco != null){
			sql += "AND p.preco = :preco ";
		}
		if(dataCadastro != null){
			sql += "ANDp.dataCadastro = : dataCadastro";
		}
		TypedQuery<Produto> query = this.em.createQuery(sql, Produto.class);
		if(nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}
		if(preco != null){
			query.setParameter("preco", preco);
		}
		if(dataCadastro != null){
			query.setParameter("dataCadastro", dataCadastro);
		}

		return query.getResultList();
	}

	public List<Produto> buscaPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);

		Predicate filtros = builder.and();
		if(nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		if(preco != null){
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if(dataCadastro != null){
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}

		query.where(filtros);

		return em.createQuery(query).getResultList();

	}

	public BigDecimal buscarPrecoDoProduto(String nome) {

		return this.em.createQuery("SELECT p.preco FROM Produto p WHERE p.nome = :nome", BigDecimal.class )
				.setParameter("nome", nome)
				.getSingleResult();
	}



}
