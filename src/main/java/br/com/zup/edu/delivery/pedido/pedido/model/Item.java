package br.com.zup.edu.delivery.pedido.pedido.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pratoId;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate.
     */
    @Deprecated
    public Item() {
    }

    public Item(Long pratoId, BigDecimal valorUnitario, Integer quantidade) {
        this.pratoId = pratoId;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public Long getPratoId() {
        return pratoId;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorUnitario.multiply(BigDecimal.valueOf((long) quantidade));
    }

}
