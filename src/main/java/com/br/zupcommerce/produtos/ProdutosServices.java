package com.br.zupcommerce.produtos;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosServices {
    public List<String> getProdutos(){
        return List.of("sofá","cama", "geladeira");
    }
}
