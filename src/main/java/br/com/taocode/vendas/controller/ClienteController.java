package br.com.taocode.vendas.controller;

import br.com.taocode.vendas.domain.entity.Cliente;
import br.com.taocode.vendas.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value="/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteJpaRepository;

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

    public ResponseEntity save() {

        //return ResponseEntity.ok(cliente.get());
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
