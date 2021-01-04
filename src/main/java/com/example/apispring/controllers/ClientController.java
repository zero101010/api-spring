package com.example.apispring.controllers;

import com.example.apispring.Models.Client;
import com.example.apispring.repositories.ClientRepository;
import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> getById(@PathVariable("id") Long id){
        return clientRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable("id") Long id){
        clientRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable("id") Long id,@RequestBody Client client){
        clientRepository.findById(id).map(data ->{
            data.setName(client.getName());
            data.setYear(client.getYear());
            Client updated = clientRepository.save(data);
            return updated;
        });
    }

}
