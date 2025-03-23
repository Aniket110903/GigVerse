package demo.demo.controller;

import demo.demo.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServices userServices;

//    @PostMapping
//    public ResponseEntity<ResponseGeneral<LoginResponse>> fetchuser(){
//
//    }

}
