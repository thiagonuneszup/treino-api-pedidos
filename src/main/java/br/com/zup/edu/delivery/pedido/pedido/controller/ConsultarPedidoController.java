package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.controller.response.DetalharPedidoResponse;
import br.com.zup.edu.delivery.pedido.pedido.controller.response.ItemResponse;
import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import br.com.zup.edu.delivery.pedido.pedido.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pedidos")
public class ConsultarPedidoController {

    private final PedidoRepository pedidoRepository;


    public ConsultarPedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultarPedido(@PathVariable Long id, 
                                             @RequestParam(value = "deveBuscarItens", required = false) boolean deveBuscarItens,
                                             @RequestParam(value = "deveBuscarEndereco", required = false) boolean deveBuscarEndereco) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Este pedido não existe"));

        return ResponseEntity.ok(new DetalharPedidoResponse(pedido, deveBuscarItens, deveBuscarEndereco));
    }
}
