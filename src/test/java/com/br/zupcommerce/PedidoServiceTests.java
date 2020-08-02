package com.br.zupcommerce;

import com.br.zupcommerce.pedidos.InvalidPedidoException;
import com.br.zupcommerce.pedidos.PedidoDTO;
import com.br.zupcommerce.pedidos.PedidosRepository;
import com.br.zupcommerce.pedidos.PedidosService;
import com.br.zupcommerce.produtos.Produto;
import com.br.zupcommerce.produtos.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTests {
    @Mock
    private PedidosRepository pedidosRepository;
    @Mock
    private ProdutoRepository produtoRepository;
    @InjectMocks
    private PedidosService pedidosService;

//    @Test
//    void testIfSKUExists () {
//        List<String> skuList = List.of("SOFCAMBR1", "SOFCAMBR2");
//        Mockito.when(produtoRepository.findBySkuIn(Mockito.anyString()).thenReturn(List.of(new Produto())));
//    }
    @Test
    void testNullNomeCliente() {
        PedidoDTO pedidoDTO = PedidoDTO.builder().nomeCliente(null).build();
        InvalidPedidoException exception = assertThrows(InvalidPedidoException.class,
                () -> pedidosService.validatePedido(pedidoDTO));
        assertEquals("NomeCliente is required", exception.getMessage());
    }

    @Test
    void testEmplyNomeCliente() {
        PedidoDTO pedidoDTO = PedidoDTO.builder().nomeCliente("").build();
        InvalidPedidoException exception = assertThrows(InvalidPedidoException.class,
                () -> pedidosService.validatePedido(pedidoDTO));
        assertEquals("NomeCliente is empty", exception.getMessage());
    }

    @Test
    void testNullSKUList() {
        PedidoDTO pedidoDTO = PedidoDTO.builder().nomeCliente("joana").SKU(null).build();
        InvalidPedidoException exception = assertThrows(InvalidPedidoException.class,
                () -> pedidosService.validatePedido(pedidoDTO));
        assertEquals("Null SKU", exception.getMessage());
    }

    @Test
    void testEmplySKUList() {
        PedidoDTO pedidoDTO = PedidoDTO.builder().nomeCliente("joana").SKU(Collections.emptyList()).build();
        InvalidPedidoException exception = assertThrows(InvalidPedidoException.class,
                () -> pedidosService.validatePedido(pedidoDTO));
        assertEquals("Empty SKU", exception.getMessage());
    }

    @Test
    void testCalculateValorProdutos() {
        Set<Produto> produtos = Set.of(
                Produto.builder().preco(10.5f).build(),
                Produto.builder().preco(5.5f).build()
        );
        assertEquals(16.0f, pedidosService.calculateValorProdutos(produtos));
    }

    @Test
    void testValorProdutosOnValorTotal() {
        InvalidPedidoException exception = assertThrows(InvalidPedidoException.class,
                () -> pedidosService.calculateValorTotal(0.0, 0.1));
        assertEquals("Valor de produto é igual a 0", exception.getMessage());
    }
}
