package com.pizzaria.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "fornada")
public class Fornada {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idFornada;

    @Column (nullable = false)
    private int numFornada;

    @Column (nullable = false)
    private Long qtdPizzas;

    public Fornada() {
    }

    /* criou tabela pedido_fornada
    @ManyToMany(mappedBy = "fornadas")
    private List<Pedido> pedidos;*/

    public int getIdFornada() {
        return idFornada;
    }

    public void setIdFornada(int idFornada) {
        this.idFornada = idFornada;
    }

    public int getNumFornada() {
        return numFornada;
    }

    public void setNumFornada(int numFornada) {
        this.numFornada = numFornada;
    }

    public Long getQtdPizzas() {
        return qtdPizzas;
    }

    public void setQtdPizzas(Long qtdPizzas) {
        this.qtdPizzas = qtdPizzas;
    }
}
