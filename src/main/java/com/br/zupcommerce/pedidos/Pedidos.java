package com.br.zupcommerce.pedidos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(schema = "ezup", name = "pedidos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCliente;
    private  String telefone;
    private float valorProduto;
    private float valorDesconto;
    private float valorTotal;
}
