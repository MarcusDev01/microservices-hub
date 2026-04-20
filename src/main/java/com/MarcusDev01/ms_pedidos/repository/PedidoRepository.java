package com.MarcusDev01.ms_pedidos.repository;

import com.MarcusDev01.ms_pedidos.entitites.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
