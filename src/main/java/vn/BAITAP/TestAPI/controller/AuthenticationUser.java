package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.dto.InforLoginSucess;
import vn.BAITAP.TestAPI.domain.dto.UserLogin;
import vn.BAITAP.TestAPI.service.UserService;
import vn.BAITAP.TestAPI.service.Except.NoUserByRefreshToken;
import vn.BAITAP.TestAPI.util.GenerateToken;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationUser {

        private AuthenticationManagerBuilder authenticationManagerBuilder;
        private GenerateToken generateToken;
        private UserService userService;

        public AuthenticationUser(AuthenticationManagerBuilder authenticationManagerBuilder,
                        GenerateToken generateToken,
                        UserService userService) {
                this.authenticationManagerBuilder = authenticationManagerBuilder;
                this.generateToken = generateToken;
                this.userService = userService;
        }

        @PostMapping("/login")
        public ResponseEntity<?> postMethodName(@Valid @RequestBody UserLogin userLogin) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userLogin.getEmail(), userLogin.getPassword());

                Authentication authentication = this.authenticationManagerBuilder.getObject()
                                .authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                User user = this.userService.findUserByEmail(userLogin.getEmail());

                String accessToken = this.generateToken.createAccessToken(authentication);
                String refreshToken = this.generateToken.createRefreshToken(user.getEmail());

                user.setRefreshToken(refreshToken);
                this.userService.saveUser(user);

                InforLoginSucess ls = new InforLoginSucess();
                ls.setName(user.getName());
                ls.setEmail(user.getEmail());
                ls.setRole(user.getRole().getName());
                ls.setAccessToken(accessToken);
                ls.setRefreshToken(refreshToken);

                ResponseCookie springCookie = ResponseCookie.from("user-login-success", refreshToken)
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .maxAge(30000)
                                .build();

                return ResponseEntity
                                .ok()
                                .header(HttpHeaders.SET_COOKIE, springCookie.toString()).body(ls);
        }

        @GetMapping("/getEmailFromRefresh")
        public ResponseEntity<?> getMethodName(
                        @CookieValue(name = "user-login-success", defaultValue = "old-refreshToken") String oldResfreshToken) {
                User user = this.userService.findUserByRefreshToken(oldResfreshToken);

                if (user == null) {
                        throw new NoUserByRefreshToken("The RefreshToken is invalid");
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                user.getEmail(), null);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                String accessToken = this.generateToken.createAccessToken(authenticationToken);
                String refreshToken = this.generateToken.createRefreshToken(user.getEmail());

                user.setRefreshToken(refreshToken);
                this.userService.saveUser(user);

                InforLoginSucess ls = new InforLoginSucess();
                ls.setName(user.getName());
                ls.setEmail(user.getEmail());
                ls.setRole(user.getRole().getName());
                ls.setAccessToken(accessToken);
                ls.setRefreshToken(refreshToken);

                ResponseCookie springCookie = ResponseCookie.from("user-login-success",
                                refreshToken)
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .maxAge(30000)
                                .build();

                return ResponseEntity
                                .ok()
                                .header(HttpHeaders.SET_COOKIE, springCookie.toString()).body(ls);
        }

        @PostMapping("/logout-s")
        public ResponseEntity<?> postMethodName() {

                User user = this.userService
                                .findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

                user.setRefreshToken("");
                this.userService.saveUser(user);

                ResponseCookie springCookie = ResponseCookie.from("user-login-success", null)
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .maxAge(0)
                                .build();

                return ResponseEntity
                                .ok()
                                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                                .body("logout success");
        }

}
