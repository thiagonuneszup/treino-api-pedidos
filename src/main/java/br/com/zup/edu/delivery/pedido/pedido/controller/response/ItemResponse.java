package br.com.zup.edu.delivery.pedido.pedido.controller.response;

import br.com.zup.edu.delivery.pedido.pedido.model.Item;

import javax.persistence.Column;
import java.math.BigDecimal;

public class ItemResponse {


    private Long pratoId;

    private BigDecimal valorUnitario;

    private Integer quantidade;

    public ItemResponse(Item item) {
        this.pratoId = item.getPratoId();
        this.valorUnitario = item.getValorUnitario();
        this.quantidade = item.getQuantidade();
    }

    public ItemResponse() {
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
}
