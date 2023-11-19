package s5_systemecommerciale_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
//@CrossOrigin("")
public class LoginController {
    @GetMapping("/home")
    public ResponseEntity<List<String>> home (){ return ResponseEntity.ok(Arrays.asList("lova","ravo"));}

    @GetMapping("secured")
    public String secured (){
        return "secured";
    }


}
