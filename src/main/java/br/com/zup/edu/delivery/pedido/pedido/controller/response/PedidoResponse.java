package br.com.zup.edu.delivery.pedido.pedido.controller.response;

import br.com.zup.edu.delivery.pedido.pedido.controller.ItemDto;
import br.com.zup.edu.delivery.pedido.pedido.model.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoResponse {

    private String cliente;

    private String restaurante;

    private List<ItemResponse> itens = new ArrayList<>();

    private String endereco;

    private String observacao;

    private TipoPagamento tipoPagamento;

    private BigDecimal total;

    private StatusPedido status;

    public PedidoResponse(Pedido pedido) {
        this.cliente = pedido.getCliente().getNome();
        this.restaurante = pedido.getRestaurante().getNome();
        this.itens = pedido.getItens().stream().map(ItemResponse::new).collect(Collectors.toList());
        this.endereco = pedido.getEndereco().toString();
        this.observacao = pedido.getObservacao();
        this.tipoPagamento = pedido.getTipoPagamento();
        this.total = pedido.getTotal();
        this.status = pedido.getStatus();
    }

    public String getCliente() {
        return cliente;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public List<ItemResponse> getItens() {
        return itens;
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
}
