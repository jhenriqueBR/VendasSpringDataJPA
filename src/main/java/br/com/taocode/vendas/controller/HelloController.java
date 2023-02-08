package br.com.taocode.vendas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping(value="/hello")
public class HelloController {
    //@GetMapping(value="/hello/{nome}")
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello ( @RequestParam("nome") String clienteNome ) {
        return String.format("Olá %s, seja Bem Vindo!!!", clienteNome);
    }
}
