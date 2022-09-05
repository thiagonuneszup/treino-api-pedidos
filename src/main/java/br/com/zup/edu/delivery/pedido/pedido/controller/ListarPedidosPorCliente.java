package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.controller.response.PedidoPorClienteResponse;
import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import br.com.zup.edu.delivery.pedido.pedido.model.StatusPedido;
import br.com.zup.edu.delivery.pedido.pedido.repository.ClienteRepository;
import br.com.zup.edu.delivery.pedido.pedido.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class ListarPedidosPorCliente {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;


    public ListarPedidosPorCliente(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }


    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> pedidosPorCliente(@PathVariable Long clienteId,
                                               @RequestParam(required = false) LocalDateTime desde,
                                               @RequestParam(required = false) LocalDateTime ate,
                                               @RequestParam(required = false) StatusPedido status) {



        if(desde == null && ate == null && status == null) {
            List<PedidoPorClienteResponse> pedidos = pedidoRepository
                    .findAllByClienteId(clienteId)
                    .stream()
                    .map(PedidoPorClienteResponse::new).toList();
            return ResponseEntity.ok(pedidos);
        }

        return ResponseEntity.ok(pedidos);
    }
}
