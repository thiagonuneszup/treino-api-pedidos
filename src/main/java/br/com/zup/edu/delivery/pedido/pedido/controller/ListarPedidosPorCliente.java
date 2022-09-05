package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.controller.response.PedidoPorClienteResponse;
import br.com.zup.edu.delivery.pedido.pedido.model.StatusPedido;
import br.com.zup.edu.delivery.pedido.pedido.repository.ClienteRepository;
import br.com.zup.edu.delivery.pedido.pedido.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class ListarPedidosPorCliente {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;


    public ListarPedidosPorCliente(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    // SELECT * FROM SV_COLETA WHERE EMISSAO BETWEEN '01.10.2016' AND '30.10.2016'

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> pedidosPorCliente(@PathVariable Long clienteId,
                                               @RequestParam(required = false) LocalDateTime desde,
                                               @RequestParam(required = false) LocalDateTime ate,
                                               @RequestParam(required = false) StatusPedido status) {


        if (status == null){
            List<PedidoPorClienteResponse> pedidos = pedidoRepository
                    .findAllByClienteId(
                            clienteId
                            )
                    .stream()
                    .map(PedidoPorClienteResponse::new).toList();

              return ResponseEntity.ok(pedidos);

        }


        List<PedidoPorClienteResponse> pedidos = pedidoRepository
                .findAllByClienteIdAndStatus(
                        clienteId,status
                )
                .stream()
                .map(PedidoPorClienteResponse::new).toList();

        return ResponseEntity.ok(pedidos);




    }
}
