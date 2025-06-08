package br.com.cadastro.controller;

import br.com.cadastro.domain.Pessoa;
import br.com.cadastro.dto.AuthDTO;
import br.com.cadastro.dto.LoginResponseDTO;
import br.com.cadastro.dto.RegisterDTO;
import br.com.cadastro.security.TokenService;
import br.com.cadastro.service.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final AuthorizationService service;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password()));

        String token = tokenService.generateJWTToken((Pessoa) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO registerDTO) {

        service.register(registerDTO);

        return ResponseEntity.ok().build();
    }
}
