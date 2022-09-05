package br.com.zup.edu.delivery.pedido.pedido.repository;

import br.com.zup.edu.delivery.pedido.pedido.model.Cliente;
import br.com.zup.edu.delivery.pedido.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public Optional<Pedido> findTopByClienteIdOrderByIdDesc(Long clientId);

    List<Pedido> findAllByClienteId(Long id);
}
