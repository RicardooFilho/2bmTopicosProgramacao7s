package br.com.cadastro.service;

import br.com.cadastro.domain.Pessoa;
import br.com.cadastro.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa findById(Long id) {

        isPessoaAutenticadaValid(id);

        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void update(Long id, Pessoa newPessoa) {

        isPessoaAutenticadaValid(id);

        repository.findById(id)
                .map(pessoa -> repository.save(newPessoa))
                .orElseThrow(EntityNotFoundException::new);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void isPessoaAutenticadaValid(Long id) {

        Pessoa autenticado = (Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals(autenticado.getId(), id)) {

            throw new IllegalArgumentException();
        }
    }
}
