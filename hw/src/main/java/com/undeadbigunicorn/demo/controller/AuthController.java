package com.undeadbigunicorn.demo.controller;

import com.undeadbigunicorn.demo.config.AuthenticationManager;
import com.undeadbigunicorn.demo.config.TokenProvider;
import com.undeadbigunicorn.demo.domain.entities.UserEntity;
import com.undeadbigunicorn.demo.domain.exceptions.BadRequestException;
import com.undeadbigunicorn.demo.domain.exceptions.NotFoundException;
import com.undeadbigunicorn.demo.domain.helpers.RequestHelper;
import com.undeadbigunicorn.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final RequestHelper requestHelper;

    private final UserService userService;

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";


    @SneakyThrows
    @PostMapping("/sign-in")
    public ResponseEntity<UserEntity> signUserIn(@Valid @RequestBody UserEntity userLoginData, HttpServletResponse response) {
        System.out.println(userLoginData);
        System.out.println(userLoginData.getPassword());

        UserEntity user = userService.findByLogin(userLoginData.getLogin())
                .orElseThrow(() -> new NotFoundException("No user with login: " + userLoginData.getLogin()));

        if (passwordEncoder.matches(userLoginData.getPassword(), user.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getLogin(),
                            user.getPassword()
                    )
            );

            log.info("User #" + user.getId() + " signed in");

            String jwt = tokenProvider.generateToken(authentication, false);
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

            return ResponseEntity.ok(user);

        } else {
            throw new NotFoundException("Invalid credentials were provided");
        }
    }

    @SneakyThrows
    @PostMapping("/sign-up")
    public ResponseEntity<UserEntity> signUserUp(@Valid @RequestBody UserEntity user) {

        if (userService.findByLogin(user.getLogin()).isPresent()) {
            throw new BadRequestException("User with this login already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.addNewUser(user);

        log.info("User #" + user.getId() + " signed up");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getLogin(),
                        user.getPassword()
                )
        );

        String jwt = tokenProvider.generateToken(authentication, false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_STRING, TOKEN_PREFIX + jwt);

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @PostMapping("/log-out")
    public void logUserOut(HttpServletRequest request) {
        UserEntity currentUser = requestHelper.getCurrentUser(request);
        log.info("User #" + currentUser.getId() + " logged out");

        request.getSession().invalidate();
    }
}