package br.com.zup.edu.delivery.pedido.pedido.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate.
     */
    @Deprecated
    public Restaurante() {
    }

    public Restaurante(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
