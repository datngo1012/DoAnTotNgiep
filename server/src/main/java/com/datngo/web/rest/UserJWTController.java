package com.datngo.web.rest;

import com.datngo.domain.NguoiDung;
import com.datngo.domain.User;
import com.datngo.repository.NguoiDungRepository;
import com.datngo.repository.UserRepository;
import com.datngo.security.jwt.JWTFilter;
import com.datngo.security.jwt.TokenProvider;
import com.datngo.web.rest.errors.BadRequestAlertException;
import com.datngo.web.rest.vm.LoginVM;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {

        try {
            Optional<User> user = userRepository.findOneByLogin(loginVM.getUsername());
            if (user.isPresent()){
                User user1 = user.get();
                if (passwordEncoder.matches(loginVM.getPassword(), user1.getPassword())) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
                    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
                    String jwt = tokenProvider.createToken(authentication, rememberMe);
                    HttpHeaders httpHeaders = new HttpHeaders();
                    NguoiDung nguoiDung = nguoiDungRepository.findOneByUserId(user.get().getId());
                    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
                    if(authentication.getAuthorities().size()>1) {
                        return new ResponseEntity<>(new JWTToken(jwt, nguoiDung, true), httpHeaders, HttpStatus.OK);
                    }
                    else return new ResponseEntity<>(new JWTToken(jwt, nguoiDung, false), httpHeaders, HttpStatus.OK);

                }
                else {
                    throw new BadRequestAlertException("Tên đăng nhập hoặc mật khẩu không đúng", "AccountOrPasswordErr", "not existed");
                }
            }
            else {
                throw new BadRequestAlertException("Tên đăng nhập hoặc mật khẩu không đúng", "AccountOrPasswordErr", "not existed");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new BadRequestAlertException("Tên đăng nhập hoặc mật khẩu không đúng", "AccountOrPasswordErr", "not existed");
        }

    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        private NguoiDung nguoiDung;

        private boolean isAdmin;

        JWTToken(String idToken, NguoiDung nguoiDung, boolean isAdmin) {
            this.idToken = idToken;
            this.nguoiDung = nguoiDung;
            this.isAdmin = isAdmin;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("is_admin")
        public boolean isAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean admin) {
            isAdmin = admin;
        }

        @JsonProperty("user_info")
        public NguoiDung getNguoiDung() {
            return nguoiDung;
        }

        public void setNguoiDung(NguoiDung nguoiDung) {
            this.nguoiDung = nguoiDung;
        }
    }
}
