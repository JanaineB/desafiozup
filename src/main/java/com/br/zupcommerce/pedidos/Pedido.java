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
    private float valorProduto;
    private float valorDesconto;
    private float valorTotal;
    @ManyToMany
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "fk_pedidos"),
            inverseJoinColumns = @JoinColumn(name = "fk_produtos"))
    private Set<Produto> produtos;
}
