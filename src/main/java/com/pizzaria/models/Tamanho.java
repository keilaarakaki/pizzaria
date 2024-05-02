package com.pizzaria.models;

import jakarta.persistence.*;

@Entity
@Table(name="tamanho")
public class Tamanho {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idTamanho;

        @Column(length = 50, nullable=false)
        private String nome;

        @Column(nullable = false)
        private float desconto;

        public Tamanho() { //contrutor vazio
        }

    public int getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(int idTamanho) {
        this.idTamanho = idTamanho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }
}
