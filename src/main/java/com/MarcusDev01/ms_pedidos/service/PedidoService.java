package com.MarcusDev01.ms_pedidos.service;
import com.MarcusDev01.ms_pedidos.dto.ItemDoPedidoDTO;
import com.MarcusDev01.ms_pedidos.dto.PedidoDTO;
import com.MarcusDev01.ms_pedidos.entitites.ItemDoPedido;
import com.MarcusDev01.ms_pedidos.entitites.Pedido;
import com.MarcusDev01.ms_pedidos.entitites.Status;
import com.MarcusDev01.ms_pedidos.exceptions.ResourceNotFoundException;
import com.MarcusDev01.ms_pedidos.repository.ItemDoPedidoRepository;
import com.MarcusDev01.ms_pedidos.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemDoPedidoRepository itemDoPedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAllPedidos() {

        return pedidoRepository.findAll()
                .stream().map(PedidoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PedidoDTO findPedidoById(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. Id: " + id)
        );

        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO savePedido(PedidoDTO pedidoDto) {

        Pedido pedido = new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setStatus(Status.CRIADO);
        mapDtoToPedido(pedidoDto, pedido);
        pedido.calcularValorTotalDoPedido();
        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO updatePedido(Long id, PedidoDTO pedidoDto) {

        try {
            Pedido pedido = pedidoRepository.getReferenceById(id);
            pedido.getItens().clear();
            pedido.setData(LocalDate.now());
            pedido.setStatus(Status.CRIADO);
            mapDtoToPedido(pedidoDto, pedido);
            pedido.calcularValorTotalDoPedido();
            pedido = pedidoRepository.save(pedido);
            return new PedidoDTO(pedido);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }
    }

    public void deletePedidoById(Long id){
        if(!pedidoRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }

        pedidoRepository.deleteById(id);
    }

    private void mapDtoToPedido(PedidoDTO pedidoDto, Pedido pedido) {

        pedido.setNome(pedidoDto.getNome());
        pedido.setCpf(pedidoDto.getCpf());

        for (ItemDoPedidoDTO itemDTO : pedidoDto.getItens()) {

            ItemDoPedido itemPedido = new ItemDoPedido();
            itemPedido.setQuantidade(itemDTO.getQuantidade());
            itemPedido.setDescricao(itemDTO.getDescricao());
            itemPedido.setPrecoUnitario(itemDTO.getPrecoUnitario());
            itemPedido.setPedido(pedido);

            pedido.getItens().add(itemPedido);
        }
    }

}