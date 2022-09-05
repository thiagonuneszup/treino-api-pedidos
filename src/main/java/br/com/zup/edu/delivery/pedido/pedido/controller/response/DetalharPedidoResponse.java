package br.com.zup.edu.delivery.pedido.pedido.controller.response;

import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import br.com.zup.edu.delivery.pedido.pedido.model.StatusPedido;
import br.com.zup.edu.delivery.pedido.pedido.model.TipoPagamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalharPedidoResponse {

    private String cliente;

    private String restaurante;

    private String endereco;

    private String observacao;

    private TipoPagamento tipoPagamento;

    private BigDecimal total;

    private StatusPedido status;

    private List<ItemResponse> itens;

    public DetalharPedidoResponse(Pedido pedido, boolean deveBuscarItens, boolean deveBuscarEndereco) {
        this.cliente = pedido.getCliente().getNome();
        this.restaurante = pedido.getRestaurante().getNome();
        this.observacao = pedido.getObservacao();
        this.tipoPagamento = pedido.getTipoPagamento();
        this.total = pedido.getTotal();
        this.status = pedido.getStatus();

        if(deveBuscarEndereco) {
            this.endereco = pedido.getEndereco().toString();
        }

        if(deveBuscarItens) {
            this.itens = pedido.getItens().stream().map(ItemResponse::new).collect(Collectors.toList());
        }
    }

    public String getCliente() {
        return cliente;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemResponse> getItens() {
        return itens;
    }
}
