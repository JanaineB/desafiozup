package com.br.zupcommerce.produtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (schema = "ezup", name = "produtos")
public class Produto {
    @Id
    //TODO: encontrar notaçao p/ informar q é auto generated
    private long id;
    private String sku;
    private String descricao;
    private float preco;
    private float peso;
    private float altura;
    private float largura;
    private float profundidade;
    private String fabricante;
    //TODO: criar builder
    public Produto() {
    }

    public Produto(long id, String sku, String descricao, float preco, float peso, float altura, float largura, float profundidade, String fabricante) {
        this.id = id;
        this.sku = sku;
        this.descricao = descricao;
        this.preco = preco;
        this.peso = peso;
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", peso=" + peso +
                ", altura=" + altura +
                ", largura=" + largura +
                ", profundidade=" + profundidade +
                ", fabricante='" + fabricante + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(float profundidade) {
        this.profundidade = profundidade;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
