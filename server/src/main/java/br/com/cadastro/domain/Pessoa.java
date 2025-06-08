package br.com.cadastro.domain;

import br.com.cadastro.dto.RegisterDTO;
import br.com.cadastro.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Pessoa ofRegister(String encodedPassword, RegisterDTO registerDTO) {

        Pessoa newPessoa = new Pessoa();
        newPessoa.setName(registerDTO.name());
        newPessoa.setEmail(registerDTO.email());
        newPessoa.setRole(registerDTO.role());
        newPessoa.setPassword(encodedPassword);

        return newPessoa;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Role.ADMIN.equals(this.role)) {
            return List.of(
                    new SimpleGrantedAuthority(Role.ADMIN.getRole()),
                    new SimpleGrantedAuthority(Role.USER.getRole())
            );
        }

        return List.of(new SimpleGrantedAuthority(Role.USER.getRole()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {

        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {

        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {

        return UserDetails.super.isEnabled();
    }
}
