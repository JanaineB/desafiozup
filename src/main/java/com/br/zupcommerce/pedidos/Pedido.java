package com.br.zupcommerce.pedidos;

import com.br.zupcommerce.produtos.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "ezup", name = "pedidos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCliente;
    private  String telefone;
    private double valorProdutos;
    private double valorDesconto;
    private double valorTotal;
    @ManyToMany
    @JoinTable(
            schema = "ezup",
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "PEDIDOS_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUTOS_ID"))
    private Set<Produto> produtos;
}
