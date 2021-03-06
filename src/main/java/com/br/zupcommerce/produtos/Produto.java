package com.br.zupcommerce.produtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(schema = "ezup", name = "produtos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sku;
    private String descricao;
    private float preco;
    private float peso;
    private float altura;
    private float largura;
    private float profundidade;
    private String fabricante;
}
