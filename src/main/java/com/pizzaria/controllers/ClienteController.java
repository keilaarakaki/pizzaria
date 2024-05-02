package com.pizzaria.controllers;

import com.pizzaria.models.Cliente;
import com.pizzaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(value = "/cadcliente", method = RequestMethod.POST)
    public Cliente save(@RequestBody Cliente cliente) { //requisitando os dados pelo corpo da requisição
        return clienteRepository.save(cliente);
    }

    @RequestMapping(value = "/mostracliente", method = RequestMethod.GET)
    public List<Cliente> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()){ //se for vazio - não encontrou nada
            // não preciso do Optional pois se não houver cadastro, a lst retornará vazia e não null
            throw new RuntimeException("Não há clientes cadastrados"); // envia pra nossa api
        }
        else {
            return clientes;
        }
    }

    @RequestMapping(value = "/mostracliente/{id}", method = RequestMethod.GET)
    public Cliente findById(@PathVariable long id) {
        Optional<Cliente> resultado = this.clienteRepository.findById(id);
        //optional encapsula o ingrediente encontrado ou retorna um Optional vazio se não encontrar
        if (resultado.isEmpty()) { // se for vazio
            throw new RuntimeException("Cliente não encontrado"); // envia pra nossa api
        } else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    @DeleteMapping(value = "/excluicliente/{id}")
    public Cliente deleteById(@PathVariable long id) { //vou receber por parametro um id - PathVariable
        Cliente cliente = findById(id);
        this.clienteRepository.deleteById(id);
        return cliente;
    }

    @RequestMapping(value = "/editacliente/{id}", method = RequestMethod.PUT)
    public Cliente updateById (@PathVariable long id, @RequestBody Cliente cliente){ //virá no corpo da requisição
        this.findById(id);
        cliente.setIdCliente(id);
        cliente = this.clienteRepository.save(cliente);
        return cliente;
    }






}
