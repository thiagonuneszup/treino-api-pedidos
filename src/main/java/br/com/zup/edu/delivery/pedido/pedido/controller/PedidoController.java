package br.com.zup.edu.delivery.pedido.pedido.controller;

import br.com.zup.edu.delivery.pedido.pedido.controller.response.PedidoResponse;
import br.com.zup.edu.delivery.pedido.pedido.model.Cliente;
import br.com.zup.edu.delivery.pedido.pedido.model.Endereco;
import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import br.com.zup.edu.delivery.pedido.pedido.model.Restaurante;
import br.com.zup.edu.delivery.pedido.pedido.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final EntityManager entityManager;

    public PedidoController(PedidoRepository pedidoRepository, EntityManager entityManager) {
        this.pedidoRepository = pedidoRepository;
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoResponse> fazerPedido (@RequestBody @Valid FazerPedidoRequest request, UriComponentsBuilder uriBuilder){

        Cliente cliente = Optional.ofNullable(entityManager.find(Cliente.class, request.clienteId()))
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY));

        Restaurante restaurante = Optional.ofNullable(entityManager.find(Restaurante.class, request.restauranteId()))
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY));

        Endereco endereco = Optional.ofNullable(entityManager.find(Endereco.class, request.enderecoId()))
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY));


        Optional<Pedido> ultimoPedido = pedidoRepository.findTopByClienteIdOrderByIdDesc(cliente.getId());

        Pedido pedido = request.toModel(cliente, restaurante, endereco);



        if(ultimoPedido.isPresent()) {
            LocalDateTime momentoUltimoPedido = ultimoPedido.get().getCriadoEm();

            long intervaloDoUltimoPedido = ChronoUnit.MINUTES.between(momentoUltimoPedido, pedido.getCriadoEm());

            if(intervaloDoUltimoPedido < 5) {
                throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Só é possível fazer o próximo pedido após 5 minutos");
            }
        }

        pedidoRepository.save(pedido);


        URI location = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(location).body(new PedidoResponse(pedido));
    }
}
