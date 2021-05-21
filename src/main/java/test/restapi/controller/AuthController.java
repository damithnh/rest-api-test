package test.restapi.controller;

import test.restapi.dto.LoginRequest;
import test.restapi.dto.RegisterRequest;
import test.restapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private AuthService authService;

    @Autowired
    public AuthController(RegisterRequest registerRequest,
                          LoginRequest loginRequest,
                          AuthService authService) {
        this.registerRequest = registerRequest;
        this.loginRequest = loginRequest;
        this.authService = authService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
