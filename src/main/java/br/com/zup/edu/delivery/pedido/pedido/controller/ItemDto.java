package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.model.Item;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record ItemDto(

        @NotNull
        Long pratoId,

        @Positive
        Integer quantidade,

        BigDecimal valorUnitario
) {

        public Item toModel() {
               return new Item(pratoId, valorUnitario, quantidade);
        }
}
