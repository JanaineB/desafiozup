package com.br.zupcommerce.produtos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    private ProdutosServices produtosServices;

    public ProdutosController(ProdutosServices produtosServices) {
        this.produtosServices = produtosServices;
    }

    @GetMapping
    public List<Produto> getProdutos() {
        return produtosServices.getProdutos();
    }

    @PostMapping
    public Produto postProduto(ProdutoDTO produto) {
        return produtosServices.postProduto(produto);
    }
}
