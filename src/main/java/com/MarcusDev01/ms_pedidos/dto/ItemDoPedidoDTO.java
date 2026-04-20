package com.MarcusDev01.ms_pedidos.dto;

import com.MarcusDev01.ms_pedidos.entitites.ItemDoPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDoPedidoDTO {
    private Long id;


    @NotNull(message = "quantidade requerido")
    @Positive(message = "quantidade deve ser um numero positivo")
    private  Integer quantidade;
    @NotBlank(message = "descricao requerido")
    private String descricao;

    @NotNull(message = "preco unitario requerido")
    @Positive(message = "preco unitario deve ser um numero positivo")
    private BigDecimal precoUnitario;

    public ItemDoPedidoDTO(ItemDoPedido itemDoPedido){
        id = itemDoPedido.getId();
        quantidade = itemDoPedido.getQuantidade();
        descricao = itemDoPedido.getDescricao();
        precoUnitario = itemDoPedido.getPrecoUnitario();
    }
}
