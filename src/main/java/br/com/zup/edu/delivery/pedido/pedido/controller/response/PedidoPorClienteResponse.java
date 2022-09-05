package br.com.zup.edu.delivery.pedido.pedido.controller.response;

import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import br.com.zup.edu.delivery.pedido.pedido.model.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoPorClienteResponse {

    private Long id;

    private LocalDateTime data;

    private StatusPedido status;

    private BigDecimal valor;

    public PedidoPorClienteResponse(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getCriadoEm();
        this.status = pedido.getStatus();
        this.valor = pedido.getTotal();
    }

    @Deprecated
    public PedidoPorClienteResponse() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
