package com.br.zupcommerce.produtos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Produto createProduto(ProdutoDTO produtoDTO) {
        log.info("Creating new Produto: " + produtoDTO.toString());
        return produtosServices.createProduto(produtoDTO);
    }

    @PutMapping
    public Produto updateProduto(ProdutoDTO produtoDTO) {
        log.info("Updating produto:" + produtoDTO.toString());
        return produtosServices.updateProduto(produtoDTO);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<String> deleteProduto(@PathVariable String sku) {
        produtosServices.deleteProduto(sku);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deleted SKU: " + sku);
    }
}
