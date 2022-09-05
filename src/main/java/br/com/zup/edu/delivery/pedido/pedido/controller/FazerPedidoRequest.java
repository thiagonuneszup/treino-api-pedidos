package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public record FazerPedidoRequest(

        @NotNull
        Long clienteId,

        @NotNull
        Long restauranteId,

        String observacao,

        @NotEmpty
        @Valid
        List<ItemDto> itens,

        @NotNull
        Long enderecoId
) {

        public Pedido toModel(Cliente cliente, Restaurante restaurante, Endereco endereco){

                List<Item> itemList = this.itens.stream().map(ItemDto::toModel).collect(Collectors.toList());

                return new Pedido(cliente,restaurante,itemList, endereco, observacao);
        }

}