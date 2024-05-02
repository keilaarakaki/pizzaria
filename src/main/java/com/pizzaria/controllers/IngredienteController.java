package com.pizzaria.controllers;

import com.pizzaria.models.Ingrediente;
import com.pizzaria.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;


    @RequestMapping(value = "/cadingrediente", method = RequestMethod.POST)
    public Ingrediente save(@RequestBody Ingrediente ingrediente) { //requisitando os dados pelo corpo da requisição
        return ingredienteRepository.save(ingrediente);
    }


    @RequestMapping(value = "/mostraingrediente", method = RequestMethod.GET)
    public List<Ingrediente> findAll(){
        List<Ingrediente> ingredientes = ingredienteRepository.findAll();
        if (ingredientes.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há ingredientes cadastrados"); // envia pra nossa api
        }
        else {
            return ingredientes;
        }
    }

    @RequestMapping(value = "/mostraingrdiente/{id}", method = RequestMethod.GET)
    public Ingrediente findById(@PathVariable long id) {
        Optional<Ingrediente> resultado = this.ingredienteRepository.findById(id);
        //optional encapsula o ingrediente encontrado ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Ingrediente não encontrado"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    //@RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/excluiingrediente/{id}")
    public Ingrediente deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        Ingrediente ingrediente = findById(id);
        this.ingredienteRepository.deleteById(id);
        return ingrediente;
    }

    @RequestMapping(value = "/editaingrediente/{id}", method = RequestMethod.PUT)
    public Ingrediente updateById (@PathVariable int id, @RequestBody Ingrediente ingrediente){ //virá no corpo da requisição
        this.findById(id);
        ingrediente.setIdIngrediente(id);
        ingrediente = this.ingredienteRepository.save(ingrediente);
        return ingrediente;
    }


}
