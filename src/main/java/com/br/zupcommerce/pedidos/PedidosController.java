package com.br.zupcommerce.pedidos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PedidosController {
    private PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @GetMapping
    public List<String> getPedidos(){
        return pedidosService.getPedidos();
    }
}
