package com.br.zupcommerce.produtos;

public class InvalidProdutoException extends RuntimeException{
    public InvalidProdutoException(String message) {
        super(message);
    }

}
