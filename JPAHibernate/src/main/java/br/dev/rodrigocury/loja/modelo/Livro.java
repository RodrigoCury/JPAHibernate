package br.dev.rodrigocury.loja.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Livro extends Produto{
    private String autor;

    @Column(name = "numero_de_paginas")
    private Integer numeroDePaginas;

    public Livro() {
    }

    public Livro(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor, Integer nDePaginas) {
        super(nome, descricao, preco, categoria);
        this.autor = autor;
        this.numeroDePaginas = nDePaginas;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
