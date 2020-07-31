package com.br.zupcommerce.produtos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosServices {
    private ProdutoRepository produtoRepository;

    public ProdutosServices(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Produto postProduto(ProdutoDTO produtoDTO) {
        validateProduto(produtoDTO);
        validateSKU(produtoDTO.getSku());
        Produto produto = Produto.builder()
                .sku(produtoDTO.getSku())
                .descricao(produtoDTO.getDescricao())
                .preco(produtoDTO.getPreco())
                .altura(produtoDTO.getAltura())
                .largura(produtoDTO.getLargura())
                .profundidade(produtoDTO.getProfundidade())
                .peso(produtoDTO.getPeso())
                .fabricante(produtoDTO.getFabricante())
                .build();

        return produtoRepository.save(produto);
    }

    public void validateProduto(ProdutoDTO produtoDTO) {
        if (produtoDTO.getSku() == null) {
            throw new ProdutoInvalidoException("SKU is required");
        }
        if (produtoDTO.getDescricao() == null) {
            throw new ProdutoInvalidoException("Descrição is required");
        }
        if (produtoDTO.getSku().trim().isEmpty()) {
            throw new ProdutoInvalidoException("Empty SKU");
        }
        if (produtoDTO.getDescricao().trim().isEmpty()) {
            throw new ProdutoInvalidoException("Empty Descrição");
        }
        if (produtoDTO.getPreco() < 0) {
            throw new ProdutoInvalidoException("Preço invalido");
        }
    }

    public void validateSKU(String SKU) {
        Optional<Produto> produto = produtoRepository.findBySku(SKU);
        if(produto.isPresent()){
            throw new ProdutoInvalidoException("SKU Already Exists");
        }
    }
}
