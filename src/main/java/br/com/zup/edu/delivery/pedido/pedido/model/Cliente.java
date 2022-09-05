package br.com.zup.edu.delivery.pedido.pedido.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Endereco> enderecos;

    private int quantidadeCancelamentos;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate.
     */
    @Deprecated
    public Cliente() {
    }

    public Cliente(String nome, List<Endereco> enderecos) {
        this.nome = nome;
        this.enderecos = enderecos;
        this.quantidadeCancelamentos = 0;
    }

    public Long getId() {
        return id;
    }

    public void adicionaCancelamento() {
        this.quantidadeCancelamentos++;
    }

    public String getNome() {
        return nome;
    }

    public boolean extrapolou() {
        return this.quantidadeCancelamentos > 10;
    }
}
