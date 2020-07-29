package com.br.zupcommerce.produtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private String sku;
    private String descricao;
    private float preco;
    private float peso;
    private float altura;
    private float largura;
    private float profundidade;
    private String fabricante;
}
