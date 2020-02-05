package ehss.example.jwt.microservicescore.controller;

import ehss.example.jwt.microservicescore.data.AuthRequest;
import ehss.example.jwt.microservicescore.data.AuthResponse;
import ehss.example.jwt.microservicescore.data.AuthorizationException;
import ehss.example.jwt.microservicescore.data.ErrorDetails;
import ehss.example.jwt.microservicescore.service.JwtTokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import ehss.example.jwt.microservicescore.service.KPUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthenticationController {

    private JwtTokenService jwtTokenService;
    private AuthenticationManager authenticationManager;
    private KPUserDetailService kpUserDetailService;

    @Autowired
    public AuthenticationController(JwtTokenService jwtTokenService,AuthenticationManager authenticationManager,KPUserDetailService kpUserDetailService){
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager=authenticationManager;
        this.kpUserDetailService = kpUserDetailService;
    }

    @PostMapping("/authenticate")
    @ApiOperation(value = "Authentication endpoint. Post a key and secret and get back a bearer token", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list",response = AuthResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized",response = ErrorDetails.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden",response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",response = ErrorDetails.class)
    })
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authenticationRequest) throws AuthorizationException {

            authenticate(authenticationRequest.getKey(),authenticationRequest.getSecret());
            final UserDetails userDetails = kpUserDetailService.loadUserByUsername(authenticationRequest.getKey());

            final String token = jwtTokenService.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));

    }


    private void authenticate(String username, String password) throws AuthorizationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthorizationException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new AuthorizationException("INVALID_CREDENTIALS");
        }
    }
}
