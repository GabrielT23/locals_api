package com.locals.locals_api.modules.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.locals.locals_api.modules.auth.dtos.AuthDTO;
import com.locals.locals_api.modules.auth.dtos.ResponseAuthDTO;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

@Service
public class AuthService {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseAuthDTO execute (AuthDTO authDTO) {

        var user = this.usersRepository.findByEmail(authDTO.getEmail()).orElseThrow(
            () -> {
                throw new RuntimeException("Email or password invalid");
            }
        );
        var passwordMatches = this.passwordEncoder.matches(authDTO.getPassword(), user.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Email or password invalid");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                    .withIssuer("locals")
                    .withSubject(user.getId().toString())
                    .sign(algorithm);
        ResponseAuthDTO responseAuthDTO = new ResponseAuthDTO();
        responseAuthDTO.setToken(token);

        return responseAuthDTO;

    }
}
