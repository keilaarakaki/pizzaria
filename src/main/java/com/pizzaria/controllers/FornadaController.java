package com.pizzaria.controllers;

import com.pizzaria.models.Fornada;
import com.pizzaria.repositories.FornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FornadaController {

    @Autowired
    private FornadaRepository fornadaRepository;


    @RequestMapping(value = "/cadfornada", method = RequestMethod.POST)
    public Fornada save(@RequestBody Fornada fornada) { //requisitando os dados pelo corpo da requisição
        return fornadaRepository.save(fornada);
    }


    @RequestMapping(value = "/mostrafornada", method = RequestMethod.GET)
    public List<Fornada> findAll(){
        List<Fornada> fornadas = fornadaRepository.findAll();
        if (fornadas.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há fornadas cadastradas"); // envia pra nossa api
        }
        else {
            return fornadas;
        }
    }

    @RequestMapping(value = "/mostrafornada/{id}", method = RequestMethod.GET)
    public Fornada findById(@PathVariable long id) {
        Optional<Fornada> resultado = this.fornadaRepository.findById(id);
        //optional encapsula o ingrediente encontrado ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Fornada não encontrada"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    @DeleteMapping(value = "/excluifornada/{id}")
    public Fornada deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        Fornada fornada = findById(id);
        this.fornadaRepository.deleteById(id);
        return fornada;
    }

    @RequestMapping(value = "/editafornada/{id}", method = RequestMethod.PUT)
    public Fornada updateById (@PathVariable int id, @RequestBody Fornada fornada){ //virá no corpo da requisição
        this.findById(id);
        fornada.setIdFornada(id);
        fornada = this.fornadaRepository.save(fornada);
        return fornada;
    }





}
