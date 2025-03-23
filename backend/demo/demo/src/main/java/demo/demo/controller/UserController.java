package demo.demo.controller;

import demo.demo.constants.Constants;
import demo.demo.dto.ResponseGeneral;
import demo.demo.dto.loginDTO.LoginPayload;
import demo.demo.dto.loginDTO.LoginResponse;
import demo.demo.dto.loginDTO.RegisterPayload;
import demo.demo.services.UserServices;
import demo.demo.utilis.JwtUtilis;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilis jwtUtilis;

    @GetMapping
    public ResponseEntity<ResponseGeneral<LoginResponse>> getUser(@RequestParam String id){
        try {
            return ResponseEntity.ok(ResponseGeneral.success(userServices.fetchUsers(id), "User Found Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseGeneral<LoginResponse>> register(@RequestBody RegisterPayload user){
        try {
            userServices.register(user);
            return ResponseEntity.ok(ResponseGeneral.success(null, "User Successfully Created"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseGeneral<LoginResponse>> login(@RequestBody  LoginPayload payload, HttpServletResponse response){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(payload.getEmail(),payload.getPassword())
            );
            LoginResponse user = userServices.login(payload.getEmail());
            String jwt = jwtUtilis.generateToken(user.getEmail(), user.getIsSeller());
            ResponseCookie cookie = ResponseCookie.from("accessToken", jwt)
                    .httpOnly(true)
                    .secure(true)  // Use HTTPS
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60) // 7 days
                    .sameSite("None") // For cross-origin support
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());
            return ResponseEntity.ok(ResponseGeneral.success(user, "User LoggedIn Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }
}
