package com.pizzaria.controllers;

import com.pizzaria.models.PizzaPedida;
import com.pizzaria.repositories.PizzaPedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaPedidaController {
    @Autowired
    private PizzaPedidaRepository pizzaPedidaRepository;


    @RequestMapping(value = "/cadpizzapedida", method = RequestMethod.POST)
    public PizzaPedida save(@RequestBody PizzaPedida pizzaPedida) { //requisitando os dados pelo corpo da requisição
        return pizzaPedidaRepository.save(pizzaPedida);
    }

    @RequestMapping(value = "/mostrapizzapedida", method = RequestMethod.GET)
    public List<PizzaPedida> findAll(){
        List<PizzaPedida> pizzaspedidas = pizzaPedidaRepository.findAll();
        if (pizzaspedidas.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há pizzas pedidas"); // envia pra nossa api
        }
        else {
            return pizzaspedidas;
        }
    }

    @RequestMapping(value = "/mostrapizzapedida/{id}", method = RequestMethod.GET)
    public PizzaPedida findById(@PathVariable long id) {
        Optional<PizzaPedida> resultado = this.pizzaPedidaRepository.findById(id);
        //optional encapsula o resultado encontrado ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Pizza pedida não encontrada"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    @DeleteMapping(value = "/excluipizzapedida/{id}")
    public PizzaPedida deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        PizzaPedida pizzaPedida = findById(id);
        this.pizzaPedidaRepository.deleteById(id);
        return pizzaPedida;
    }

    @RequestMapping(value = "/editapizzapedida/{id}", method = RequestMethod.PUT)
    public PizzaPedida updateById (@PathVariable Long id, @RequestBody PizzaPedida pizzaPedida){ //virá no corpo da requisição
        this.findById(id);
        pizzaPedida.setIdPizzaPedida(id);
        pizzaPedida = this.pizzaPedidaRepository.save(pizzaPedida);
        return pizzaPedida;
    }


}
