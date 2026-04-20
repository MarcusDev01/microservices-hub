package com.MarcusDev01.ms_pedidos.repository;

import com.MarcusDev01.ms_pedidos.entitites.ItemDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {
}
