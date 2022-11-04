package br.com.taocode.vendas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/api/cliente")
public class ClienteController {

    //@GetMapping(value="/hello/{nome}")
    @RequestMapping(value="/cliente", method = RequestMethod.GET)
    @ResponseBody
    public String hello ( @RequestParam("nome") String clienteNome ) {
        return String.format("Olá %s, seja Bem Vindo!!!", clienteNome);
    }
}
