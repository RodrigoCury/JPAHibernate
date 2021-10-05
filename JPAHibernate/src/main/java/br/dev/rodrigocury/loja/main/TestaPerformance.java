package br.dev.rodrigocury.loja.main;

import br.dev.rodrigocury.loja.modelo.Pedido;
import br.dev.rodrigocury.loja.util.JPAUtil;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;

public class TestaPerformance {

    public static void main(String[] args) {
        Cadastrador.populaBD();
        EntityManager em = JPAUtil.getEntity();

        em.find(Pedido.class, 1L);


    }
}
