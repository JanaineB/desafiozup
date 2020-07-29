package com.br.zupcommerce.produtos;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
