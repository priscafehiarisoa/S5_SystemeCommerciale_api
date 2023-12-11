package s5_systemecommerciale_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.user.Users;
import s5_systemecommerciale_api.service.LoginServices;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
//@CrossOrigin("")
public class LoginController {
    private final LoginServices loginServices;

    public LoginController(LoginServices loginServices) {
        this.loginServices = loginServices;
    }

    @GetMapping("/home")
    public ResponseEntity<List<String>> home (){ return ResponseEntity.ok(Arrays.asList("lova","ravo"));}

    @GetMapping("secured")
    public String secured (){
        return "secured";
    }

    @PostMapping("login")
    public HashMap<String,Object> login(@RequestBody HashMap<String,String> userDatas)  {
        Users users= new Users();
        HashMap<String, Object> connectedUser= new HashMap<>();

        try {
            users.setEmail(userDatas.get("email"));
            users.setPassword(userDatas.get("password"));
        users=loginServices.login(users);
        System.out.println(users);
        connectedUser.put("id",users.getId());
        connectedUser.put("email",users.getEmail());
        connectedUser.put("poste",users.getPoste());
        connectedUser.put("service",users.getService().getNomService());
        connectedUser.put("idservice",users.getService().getId());
        }catch (Exception expEmail){
            connectedUser.put("error",expEmail.getMessage());
        }
        return connectedUser;
    }


}
