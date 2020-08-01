package com.br.zupcommerce;

import com.br.zupcommerce.produtos.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTests {
    @Mock
    private ProdutoRepository produtoRepository;
    @InjectMocks
    private ProdutosServices produtosServices;

    @Test
    void testValidateIfSKUIsRequired() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().descricao("tv").build();

        InvalidProdutoException exception = assertThrows(InvalidProdutoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("SKU is required", exception.getMessage());
    }

    @Test
    void testValidateIfDescricaoIsRequired() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku("SKUTVBR").build();

        InvalidProdutoException exception = assertThrows(InvalidProdutoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Descrição is required", exception.getMessage());
    }

    @Test
    void testValidateIfSKUIsEmpty() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku(" ").descricao("").build();

        InvalidProdutoException exception = assertThrows(InvalidProdutoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Empty SKU", exception.getMessage());
    }

    @Test
    void testValidateIfDescricaoIsEmpty() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku("ABCD").descricao("").build();

        InvalidProdutoException exception = assertThrows(InvalidProdutoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Empty Descrição", exception.getMessage());
    }

    @Test
    void testValidateIfPrecoIsValid() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().sku("ABCD").descricao("ABC").preco(-2.5f).build();

        InvalidProdutoException exception = assertThrows(InvalidProdutoException.class,
                () -> produtosServices.validateProduto(produtoDTO));
        assertEquals("Invalid Preço", exception.getMessage());
    }

    @Test
    void testValidateIfSKUNotExists() {
        Produto produto = new Produto();
        Mockito.when(produtoRepository.findBySku(Mockito.anyString())).thenReturn(Optional.of(produto));

        InvalidProdutoException exception = assertThrows(InvalidProdutoException.class,
                () -> produtosServices.validateIfSKUAlreadyExists("SOFCAMBR1"));
        assertEquals("SKU already exists", exception.getMessage());
    }

    @Test
    void testValidateIfSKUAlreadyExists() {
        Mockito.when(produtoRepository.findBySku(Mockito.anyString())).thenReturn(Optional.empty());

        InvalidProdutoException exception = assertThrows(InvalidProdutoException.class,
                () -> produtosServices.validateSKUAndReturnProduto("SOFCAMBR1"));
        assertEquals("SKU does not exists", exception.getMessage());
    }

}
