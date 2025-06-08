package br.com.cadastro.controller;

import br.com.cadastro.domain.Pessoa;
import br.com.cadastro.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody @Valid Pessoa newPessoa) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(newPessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Pessoa newPessoa) {

        service.update(id, newPessoa);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Pessoa>> findAll(@PathVariable(value = "id") Long id) {

        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
