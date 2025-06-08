package br.com.cadastro.repository;

import br.com.cadastro.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    UserDetails findByEmail(String email);
}
