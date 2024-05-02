package com.pizzaria.controllers;

import com.pizzaria.models.Pedido;
import com.pizzaria.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @RequestMapping(value = "/cadpedido", method = RequestMethod.POST)
    public Pedido save(@RequestBody Pedido pedido) { //requisitando os dados pelo corpo da requisição
        return pedidoRepository.save(pedido);
    }


    @RequestMapping(value = "/mostrapedido", method = RequestMethod.GET)
    public List<Pedido> findAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        if (pedidos.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há pedidos cadastrados"); // envia pra nossa api
        }
        else {
            return pedidos;
        }
    }

    @RequestMapping(value = "/mostrapedido/{id}", method = RequestMethod.GET)
    public Pedido findById(@PathVariable long id) {
        Optional<Pedido> resultado = this.pedidoRepository.findById(id);
        //optional encapsula o ingrediente encontrado ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Pedido não encontrado"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    @DeleteMapping(value = "/excluipedido/{id}")
    public Pedido deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        Pedido pedido = findById(id);
        this.pedidoRepository.deleteById(id);
        return pedido;
    }

    @RequestMapping(value = "/editapedido/{id}", method = RequestMethod.PUT)
    public Pedido updateById (@PathVariable long id, @RequestBody Pedido pedido){ //virá no corpo da requisição
        this.findById(id);
        pedido.setIdPedido(id);
        pedido = this.pedidoRepository.save(pedido);
        return pedido;
    }

}
