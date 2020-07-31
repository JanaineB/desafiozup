package com.br.zupcommerce.produtos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Slf4j
public class ProdutosController {
    private ProdutosServices produtosServices;

    public ProdutosController(ProdutosServices produtosServices) {
        this.produtosServices = produtosServices;
    }

    @GetMapping
    public List<Produto> getProdutos() {
        log.info("Fetching all Produto");
        return produtosServices.getProdutos();
    }

    @PostMapping
    public Produto postProduto(ProdutoDTO produto) {
        log.info("Creating new Produto: " + produto.toString());
        return produtosServices.postProduto(produto);
    }
}
