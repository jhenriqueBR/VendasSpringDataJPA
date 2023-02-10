package br.com.taocode.vendas.controller;

import br.com.taocode.vendas.domain.entity.Cliente;
import br.com.taocode.vendas.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteJpaRepository;

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<Cliente> delete (@PathVariable("id") Integer id ) {
        Optional<Cliente> cliente = this.clienteJpaRepository.findById(id);

        if (! cliente.isPresent()) {
            //return new ResponseEntity(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }

        this.clienteJpaRepository.deleteById(cliente.get().getId());

        return ResponseEntity.noContent().build();
        //return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById (@PathVariable("id") Integer id ) {
        Optional<Cliente> cliente = this.clienteJpaRepository.findById(id);

        if (! cliente.isPresent()) {
            //return new ResponseEntity(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }

        //return ResponseEntity.ok(cliente.get());
        return new ResponseEntity(cliente.get(), HttpStatus.OK);
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        // Testando se o campos foram preenchidos

        // Testando se o nome já existe
        List<Cliente> clientesExistentes = this.clienteJpaRepository.buscarPorNome(cliente.getNome());

        if (! clientesExistentes.isEmpty()) {
            return new ResponseEntity("Este cliente já existe.", HttpStatus.CONFLICT);
        }

        // Gravando o Cliente
        Cliente clienteSalvo = this.clienteJpaRepository.save(cliente);

        //return ResponseEntity.created().body(clienteSalvo)
        return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
        /*
        Optional<Cliente> clienteExistente = this.clienteJpaRepository.findById(id);

        if (! clienteExistente.isPresent()) {
            return new ResponseEntity("Este cliente NÃO existe.", HttpStatus.NOT_FOUND);
        }

        Cliente clienteAtualizado = clienteExistente.get();
        clienteAtualizado.setNome(cliente.getNome());
        this.clienteJpaRepository.save(clienteAtualizado);

        //return ResponseEntity.created().body(clienteSalvo);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        */
        return this.clienteJpaRepository
                .findById(id)
                .map( clienteExistente -> {
                    clienteExistente.setNome(cliente.getNome());
                    clienteJpaRepository.save(clienteExistente);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }).orElseGet( () -> {
                    return new ResponseEntity("Este cliente NÃO existe.", HttpStatus.NOT_FOUND);
                });
    }
}
