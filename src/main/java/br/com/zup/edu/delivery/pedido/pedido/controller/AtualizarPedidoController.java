package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class AtualizarPedidoController {

    private final PedidoRepository pedidoRepository;

    public AtualizarPedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    //TO DO
//    public ResponseEntity<?> atualizarStatusPedido(@RequestBody AtualizaStatusResponse ) {
//    }

}
