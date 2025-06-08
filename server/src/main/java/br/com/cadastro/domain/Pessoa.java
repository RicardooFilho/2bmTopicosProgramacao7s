package br.com.cadastro.domain;

import br.com.cadastro.enums.Role;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Pessoa implements UserDetails {

    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
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
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {

        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {

        return UserDetails.super.isEnabled();
    }
}
