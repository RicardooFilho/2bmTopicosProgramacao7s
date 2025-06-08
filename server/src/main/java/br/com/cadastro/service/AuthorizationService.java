package br.com.cadastro.service;

import br.com.cadastro.domain.Pessoa;
import br.com.cadastro.dto.RegisterDTO;
import br.com.cadastro.repository.PessoaRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final PessoaRepository pessoaRepository;

    public void register(RegisterDTO registerDTO) {

        if (Objects.nonNull(loadUserByUsername(registerDTO.email()))) {

            throw new EntityExistsException();
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        pessoaRepository.save(Pessoa.ofRegister(encodedPassword, registerDTO));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return pessoaRepository.findByEmail(username);
    }
}
