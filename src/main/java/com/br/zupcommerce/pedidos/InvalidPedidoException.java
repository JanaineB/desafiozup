package com.br.zupcommerce.pedidos;

public class InvalidPedidoException extends RuntimeException{
    public InvalidPedidoException(String message) {
        super(message);
    }
}
