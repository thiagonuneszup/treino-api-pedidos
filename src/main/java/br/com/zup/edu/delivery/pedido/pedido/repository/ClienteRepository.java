package br.com.zup.edu.delivery.pedido.pedido.repository;

import br.com.zup.edu.delivery.pedido.pedido.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
