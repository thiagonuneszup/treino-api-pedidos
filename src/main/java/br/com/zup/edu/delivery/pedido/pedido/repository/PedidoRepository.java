package br.com.zup.edu.delivery.pedido.pedido.repository;

import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import br.com.zup.edu.delivery.pedido.pedido.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public Optional<Pedido> findTopByClienteIdOrderByIdDesc(Long clientId);

  List<Pedido> findAllByClienteId(Long id);


    List<Pedido> findAllByClienteIdAndStatus(Long clienteId, StatusPedido status);
}
