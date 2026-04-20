package com.MarcusDev01.ms_pedidos.controller;

import com.MarcusDev01.ms_pedidos.dto.PedidoDTO;

import com.MarcusDev01.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    private ResponseEntity<List<PedidoDTO>> getAllPedidos(){

        List<PedidoDTO> list = pedidoService.findAllPedidos();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable Long id){

        PedidoDTO pedidoDto = pedidoService.findPedidoById(id);

        return ResponseEntity.ok(pedidoDto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody @Valid PedidoDTO pedidoDto){

        pedidoDto = pedidoService.savePedido(pedidoDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pedidoDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pedidoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable Long id,
                                                  @Valid @RequestBody PedidoDTO pedidoDto){

        pedidoDto = pedidoService.updatePedido(id, pedidoDto);

        return ResponseEntity.ok(pedidoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id){

        pedidoService.deletePedidoById(id);

        return ResponseEntity.noContent().build();
    }
}
