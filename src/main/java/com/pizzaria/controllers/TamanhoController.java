package com.pizzaria.controllers;

import com.pizzaria.models.Tamanho;
import com.pizzaria.repositories.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TamanhoController {
    @Autowired
    private TamanhoRepository tamanhoRepository;


    @RequestMapping(value = "/cadtamanho", method = RequestMethod.POST)
    public Tamanho save(@RequestBody Tamanho tamanho) { //requisitando os dados pelo corpo da requisição
        return tamanhoRepository.save(tamanho);
    }

    @RequestMapping(value = "/mostratamanho", method = RequestMethod.GET)
    public List<Tamanho> findAll(){
        List<Tamanho> tamanhos = tamanhoRepository.findAll();
        if (tamanhos.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há tamanhos cadastrados"); // envia pra nossa api
        }
        else {
            return tamanhos;
        }
    }

    @RequestMapping(value = "/mostratamanho/{id}", method = RequestMethod.GET)
    public Tamanho findById(@PathVariable long id) {
        Optional<Tamanho> resultado = this.tamanhoRepository.findById(id);
        //optional encapsula o tamanho encontrado ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Tamanho não encontrado"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    //@RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/excluitamanho/{id}")
    public Tamanho deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        Tamanho tamanho = findById(id);
        this.tamanhoRepository.deleteById(id);
        return tamanho;
    }

    @RequestMapping(value = "/editatamanho/{id}", method = RequestMethod.PUT)
    public Tamanho updateById (@PathVariable int id, @RequestBody Tamanho tamanho){ //virá no corpo da requisição
        this.findById(id);
        tamanho.setIdTamanho(id);
        tamanho = this.tamanhoRepository.save(tamanho);
        return tamanho;
    }


}
