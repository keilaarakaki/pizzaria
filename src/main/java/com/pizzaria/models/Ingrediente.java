package com.pizzaria.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idIngrediente;

    @Column (length = 50, nullable = false)
    private String nome;

    @Column (nullable = false)
    private float preco;

    @ManyToMany(mappedBy = "ingredientes")
    private List<PizzaPedida> pizzasPedidas;

    public Ingrediente() {
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
