package com.br.zupcommerce.produtos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutosController {
    private ProdutosServices produtosServices;

    public ProdutosController(ProdutosServices produtosServices) {
        this.produtosServices = produtosServices;
    }

    @GetMapping("/produtos")
    public List<String> getProdutos(){
        return produtosServices.getProdutos();
    }

}
