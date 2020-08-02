package com.br.zupcommerce.pedidos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Slf4j
public class PedidosController {
    private PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @GetMapping
    public List<Pedido> getPedidos() {
        return pedidosService.getPedidos();
    }

    @PostMapping
    public Pedido createPedido(PedidoDTO pedidoDTO) {
        return pedidosService.createPedidos(pedidoDTO);
    }

    @PutMapping ("/{id}")
    public Pedido updatePedido(@PathVariable long id, PedidoDTO pedidoDTO) {
        return pedidosService.updatePedido(id, pedidoDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable long id) {
        pedidosService.deletePedido(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deleted Pedido: " + id);
    }
}
