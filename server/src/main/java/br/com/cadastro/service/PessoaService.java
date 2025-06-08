package br.com.cadastro.service;

import br.com.cadastro.domain.Pessoa;
import br.com.cadastro.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa create(Pessoa newPessoa) {
        return repository.save(newPessoa);
    }

    public void update(Long id, Pessoa newPessoa) {
        repository.findById(id)
                .map(pessoa -> repository.save(newPessoa))
                .orElseThrow(EntityNotFoundException::new);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
