package com.br.zupcommerce.pedidos;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosService {

    public List<String> getPedidos(){
        return List.of("a");
    }
}
