package com.pizzaria.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="bebida")
public class Bebida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBebida;

    @Column(length = 50, nullable=false)
    private String nome;

    @Column(nullable = false)
    private float preco;

    public Bebida() { //contrutor vazio
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

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }
}
