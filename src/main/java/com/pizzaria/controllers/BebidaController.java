package com.pizzaria.controllers;

import com.pizzaria.models.Bebida;
import com.pizzaria.repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BebidaController {

    @Autowired
    private BebidaRepository bebidaRepository;

/* troquei esse método para fazer a solicitação pelo corpo da requisição
    @RequestMapping(value = "/cadbebida", method = RequestMethod.POST)
    public Bebida save() {
        Bebida bebida = new Bebida();
        bebida.setNome("Café");
        bebida.setPreco(3.20f);
        bebida = this.bebidaRepository.save(bebida);
        return bebida;
    }
*/

    @RequestMapping(value = "/cadbebida", method = RequestMethod.POST)
    public Bebida save(@RequestBody Bebida bebida) { //requisitando os dados pelo corpo da requisição
        return bebidaRepository.save(bebida);
    }

    @RequestMapping(value = "/mostrabebida", method = RequestMethod.GET)
    public List<Bebida> findAll(){
        List<Bebida> bebidas = bebidaRepository.findAll();
        if (bebidas.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há bebidas cadastradas"); // envia pra nossa api
        }
        else {
            return bebidas;
        }
    }

    @RequestMapping(value = "/mostrabebida/{id}", method = RequestMethod.GET)
    public Bebida findById(@PathVariable long id) {
        Optional<Bebida> resultado = this.bebidaRepository.findById(id);
        //optional encapsula a bebida encontrada ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Bebida não encontrada"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    //@RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/excluibebida/{id}")
    public Bebida deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        Bebida bebida = findById(id);
        this.bebidaRepository.deleteById(id);
        return bebida;
    }

    @RequestMapping(value = "/editabebida/{id}", method = RequestMethod.PUT)
    public Bebida updateById (@PathVariable int id, @RequestBody Bebida bebida){ //virá no corpo da requisição
        this.findById(id);
        bebida.setIdBebida(id);
        bebida = this.bebidaRepository.save(bebida);
        return bebida;
    }

}
