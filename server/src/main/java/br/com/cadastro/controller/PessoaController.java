package br.com.cadastro.controller;

import br.com.cadastro.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;
}
