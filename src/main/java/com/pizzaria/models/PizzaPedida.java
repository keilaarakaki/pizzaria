package com.pizzaria.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "pizzaPedida")
public class PizzaPedida {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idPizzaPedida;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false) //é o id de Pedido nome q consta na tabela
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idPizza", nullable = false) //é o id de Pizza, coloquei td como id, mas é o nome q consta na tabela
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "idTamanho", nullable = false) //é o id de Tamanho, coloquei td como id, mas é o nome q consta na tabela
    private Tamanho tamanho;

    @ManyToMany
    @JoinTable(
            name = "PizzaPedida_has_Ingredientes",
            joinColumns = @JoinColumn(name = "idPizzaPedida"),
            inverseJoinColumns = @JoinColumn(name = "idIngrediente")
    )
    private List<Ingrediente> ingredientes;

    public PizzaPedida() {
    }

    public Long getIdPizzaPedida() {
        return idPizzaPedida;
    }

    public void setIdPizzaPedida(Long idPizzaPedida) {
        this.idPizzaPedida = idPizzaPedida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}
