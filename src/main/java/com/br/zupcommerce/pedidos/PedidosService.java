package com.br.zupcommerce.pedidos;

import com.br.zupcommerce.produtos.Produto;
import com.br.zupcommerce.produtos.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidosService {
    private PedidosRepository pedidosRepository;
    private ProdutoRepository produtoRepository;

    public PedidosService(PedidosRepository pedidosRepository, ProdutoRepository produtoRepository) {
        this.pedidosRepository = pedidosRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Pedido> getPedidos() {
        return pedidosRepository.findAll();
    }

    public Pedido createPedidos(PedidoDTO pedidoDTO) {
        validatePedido(pedidoDTO);
        Set<Produto> produto = validateSKUAndReturnProduto(pedidoDTO.getSKU());
        double valorProdutos = calculateValorProdutos(produto);
        double valorTotal = calculateValorTotal(valorProdutos, pedidoDTO.getValorDesconto());

        Pedido pedido = Pedido.builder()
                .nomeCliente(pedidoDTO.getNomeCliente())
                .telefone(pedidoDTO.getTelefone())
                .valorProdutos(valorProdutos)
                .valorDesconto(pedidoDTO.getValorDesconto())
                .valorTotal(valorTotal)
                .produtos(produto)
                .build();

        return pedidosRepository.save(pedido);
    }

    public Pedido updatePedido(long id, PedidoDTO pedidoDTO) {
        validatePedido(pedidoDTO);

        Set<Produto> produtos = validateSKUAndReturnProduto(pedidoDTO.getSKU());

        validateIfPedidoExists(id);

        double valorProdutos = calculateValorProdutos(produtos);
        double valorTotal = calculateValorTotal(valorProdutos, pedidoDTO.getValorDesconto());

        //Permiti a atualização de todos os campos por conveniência
        Pedido pedidoToUpdate = Pedido.builder()
                .id(id)
                .nomeCliente(pedidoDTO.getNomeCliente())
                .telefone(pedidoDTO.getTelefone())
                .valorDesconto(pedidoDTO.getValorDesconto())
                .produtos(produtos)
                .valorProdutos(valorProdutos)
                .valorTotal(valorTotal)
                .build();

        return pedidosRepository.save(pedidoToUpdate);
    }

    public void deletePedido(long id) {
        validateIfPedidoExists(id);
        pedidosRepository.deleteById(id);
    }

    private void validateIfPedidoExists(long id) {
        pedidosRepository.findById(id).orElseThrow(() -> new InvalidPedidoException("Pedido does not exists"));
    }

    public void validatePedido(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getNomeCliente() == null) {
            throw new InvalidPedidoException("NomeCliente is required");
        }
        if (pedidoDTO.getNomeCliente().trim().isEmpty()) {
            throw new InvalidPedidoException("NomeCliente is empty");
        }
        if (pedidoDTO.getSKU() == null) {
            throw new InvalidPedidoException("Null SKU");
        }
        if (pedidoDTO.getSKU().isEmpty()) {
            throw new InvalidPedidoException("Empty SKU");
        }
        if (pedidoDTO.getValorDesconto() > 1.0) {
            throw new InvalidPedidoException("Valor de desconto é maior que 100%");
        }
    }

    public Set<Produto> validateSKUAndReturnProduto(List<String> sku) {
        List<Produto> produto = produtoRepository.findBySkuIn(sku);

        if (produto.size() != sku.size()) {
            throw new InvalidPedidoException("One or more SKUs does not exists");
        }
        Set<Produto> setProdutos = new HashSet<>(produto);

        return setProdutos;
    }

    public double calculateValorProdutos(Set<Produto> produto) {
        return produto.stream().mapToDouble(Produto::getPreco).sum();
    }

    public double calculateValorTotal(double valorProdutos, double valorDesconto) {
        if (valorProdutos <= 0) {
            throw new InvalidPedidoException("Valor de produto é igual a 0");
        }

        return valorProdutos - (valorProdutos * valorDesconto);
    }
}
