package com.pizzaria.controllers;

import com.pizzaria.models.BebidaPedida;
import com.pizzaria.repositories.BebidaPedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BebidaPedidaController {

    @Autowired
    private BebidaPedidaRepository bebidaPedidaRepository;

    @RequestMapping(value = "/cadbebidapedida", method = RequestMethod.POST)
    public BebidaPedida save(@RequestBody BebidaPedida bebidaPedida) {
        return bebidaPedidaRepository.save(bebidaPedida);
    }

    @RequestMapping(value = "/mostrabebidapedida", method = RequestMethod.GET)
    public List<BebidaPedida> findAll(){
        List<BebidaPedida> bebidasPedidas = bebidaPedidaRepository.findAll();
        if (bebidasPedidas.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há bebida pedida cadastrada"); // envia pra nossa api
        }
        else {
            return bebidasPedidas;
        }
    }

    @RequestMapping(value = "/mostrabebidapedida/{id}", method = RequestMethod.GET)
    public BebidaPedida findById(@PathVariable long id) {
        return bebidaPedidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bebida pedida não encontrada! ID: " + id));

/*        Optional<BebidaPedida> resultado = this.bebidaPedidaRepository.findById(id);
        //optional encapsula a bebida encontrada ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Bebida pedida não encontrada"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }*/
    }

    @DeleteMapping(value = "/excluibebidapedida/{id}")
    public BebidaPedida deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        BebidaPedida bebidaPedida = findById(id);
        this.bebidaPedidaRepository.deleteById(id);
        return bebidaPedida;
    }


    @RequestMapping(value = "/editabebidapedida/{id}", method = RequestMethod.PUT)
    public BebidaPedida updateById (@PathVariable long id, @RequestBody BebidaPedida bebidaPedida){ //virá no corpo da requisição
        this.findById(id);
        bebidaPedida.setIdBebidaPedida(id);
        bebidaPedida = this.bebidaPedidaRepository.save(bebidaPedida);
        return bebidaPedida;
    }

}
