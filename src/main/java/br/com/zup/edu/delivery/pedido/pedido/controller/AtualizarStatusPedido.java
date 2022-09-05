package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.controller.response.PedidoResponse;
import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import br.com.zup.edu.delivery.pedido.pedido.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pedidos")
public class AtualizarStatusPedido {

    private final PedidoRepository pedidoRepository;

    public AtualizarStatusPedido(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        pedido.atualizaStatus();

        pedidoRepository.save(pedido);

        return ResponseEntity.ok(new PedidoResponse(pedido));
    }
}
