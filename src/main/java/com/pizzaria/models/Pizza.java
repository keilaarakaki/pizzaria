package com.pizzaria.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idPizza;

    @Column (length = 50, nullable = false)
    private String nome;

    @Column (nullable = false)
    private float precoBase;

    @Column
    private boolean personalizada;

    public Pizza() {
    }

    public int getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }

    public boolean isPersonalizada() {
        return personalizada;
    }

    public void setPersonalizada(boolean personalizada) {
        this.personalizada = personalizada;
    }
}
