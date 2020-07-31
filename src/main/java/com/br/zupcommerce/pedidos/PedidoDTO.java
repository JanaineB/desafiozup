package com.br.zupcommerce.pedidos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private String nomeCliente;
    private String telefone;
    private float valorProduto;
    private float valorDesconto;
    private float valorTotal;
    private List<String> SKU;
}
