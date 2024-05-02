package com.pizzaria.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bebida_pedida")
public class BebidaPedida {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idBebidaPedida;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "idBebida", nullable = false) // mesmo nome q está na tabela
    private Bebida bebida;

    @ManyToOne //um pedido pode ter várias BebidaPedida
    @JoinColumn(name = "idPedido", nullable = false) // id de pedido
    private Pedido pedido;


    public BebidaPedida() { //contrutor vazio
    }

    public Long getIdBebidaPedida() {
        return idBebidaPedida;
    }

    public void setIdBebidaPedida(Long idBebidaPedida) {
        this.idBebidaPedida = idBebidaPedida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }
}
