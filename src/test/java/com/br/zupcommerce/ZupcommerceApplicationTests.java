package com.br.zupcommerce;

import com.br.zupcommerce.produtos.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ZupcommerceApplicationTests {
    @Mock
    private ProdutoRepository produtoRepository;
    @InjectMocks
    private ProdutosServices produtosServices;

    @Test
    void testValidateIfSKUIsRequired() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().descricao("tv").build();

        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("SKU is required", exception.getMessage());
    }

    @Test
    void testValidateIfDescricaoIsRequired() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku("SKUTVBR").build();

        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Descrição is required", exception.getMessage());
    }

    @Test
    void testValidateIfSKUIsEmpty() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku(" ").descricao("").build();

        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Empty SKU", exception.getMessage());
    }

    @Test
    void testValidateIfDescricaoIsEmpty() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku("ABCD").descricao("").build();

        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Empty Descrição", exception.getMessage());
    }

    @Test
    void testValidateIfPrecoIsValid() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku("ABCD").descricao("ABC").preco(-2.5f).build();

        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Preço invalido", exception.getMessage());
    }

    @Test
    void testValidateIfSKUAlreadyExists(){
        Produto produto = new Produto();
        Mockito.when(produtoRepository.findBySku(Mockito.anyString())).thenReturn(Optional.of(produto));

        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class,
                () -> produtosServices.validateSKU("SOFCAMBR1"));
        assertEquals("SKU Already Exists", exception.getMessage());
    }

}
