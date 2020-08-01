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

    public Produto createProduto(ProdutoDTO produtoDTO) {
        validateProduto(produtoDTO);
        validateIfSKUAlreadyExists(produtoDTO.getSku());

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

    public Produto updateProduto(ProdutoDTO produtoDTO) {
        validateProduto(produtoDTO);
        Produto produto = validateSKUAndReturnProduto(produtoDTO.getSku());

        Produto produtoToUpdate = Produto.builder()
                .id(produto.getId())
                .sku(produtoDTO.getSku())
                .descricao(produtoDTO.getDescricao())
                .preco(produtoDTO.getPreco())
                .altura(produtoDTO.getAltura())
                .largura(produtoDTO.getLargura())
                .profundidade(produtoDTO.getProfundidade())
                .peso(produtoDTO.getPeso())
                .fabricante(produtoDTO.getFabricante())
                .build();

        return produtoRepository.save(produtoToUpdate);
    }

    //Em um cenário real eu optaria pela deleção lógica
    public void deleteProduto(String sku) {
        Produto produto = validateSKUAndReturnProduto(sku);

        produtoRepository.deleteById(produto.getId());
    }

    public void validateProduto(ProdutoDTO produtoDTO) {
        if (produtoDTO.getSku() == null) {
            throw new InvalidProdutoException("SKU is required");
        }
        if (produtoDTO.getDescricao() == null) {
            throw new InvalidProdutoException("Descrição is required");
        }
        if (produtoDTO.getSku().trim().isEmpty()) {
            throw new InvalidProdutoException("Empty SKU");
        }
        if (produtoDTO.getDescricao().trim().isEmpty()) {
            throw new InvalidProdutoException("Empty Descrição");
        }
        if (produtoDTO.getPreco() < 0) {
            throw new InvalidProdutoException("Invalid Preço");
        }
    }

    public void validateIfSKUAlreadyExists(String sku) {
        Optional<Produto> produto = produtoRepository.findBySku(sku);

        if (produto.isPresent()) {
            throw new InvalidProdutoException("SKU already exists");
        }
    }

    public Produto validateSKUAndReturnProduto(String sku) {
        Optional<Produto> produto = produtoRepository.findBySku(sku);

        return produto.orElseThrow(() -> new InvalidProdutoException("SKU does not exists"));
    }
}
