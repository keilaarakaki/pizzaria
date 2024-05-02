package com.pizzaria.controllers;

import com.pizzaria.models.Pizza;
import com.pizzaria.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;


    @RequestMapping(value = "/cadpizza", method = RequestMethod.POST)
    public Pizza save(@RequestBody Pizza pizza) { //requisitando os dados pelo corpo da requisição
        return pizzaRepository.save(pizza);
    }


    @RequestMapping(value = "/mostrapizza", method = RequestMethod.GET)
    public List<Pizza> findAll(){
        List<Pizza> pizzas = pizzaRepository.findAll();
        if (pizzas.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há pizzas cadastradas"); // envia pra nossa api
        }
        else {
            return pizzas;
        }
    }

    @RequestMapping(value = "/mostrapizza/{id}", method = RequestMethod.GET)
    public Pizza findById(@PathVariable long id) {
        Optional<Pizza> resultado = this.pizzaRepository.findById(id);
        //optional encapsula o ingrediente encontrado ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Pizza não encontrada"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    @DeleteMapping(value = "/excluipizza/{id}")
    public Pizza deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        Pizza pizza = findById(id);
        this.pizzaRepository.deleteById(id);
        return pizza;
    }

    @RequestMapping(value = "/editapizza/{id}", method = RequestMethod.PUT)
    public Pizza updateById (@PathVariable int id, @RequestBody Pizza pizza){ //virá no corpo da requisição
        this.findById(id);
        pizza.setIdPizza(id);
        pizza = this.pizzaRepository.save(pizza);
        return pizza;
    }



}
