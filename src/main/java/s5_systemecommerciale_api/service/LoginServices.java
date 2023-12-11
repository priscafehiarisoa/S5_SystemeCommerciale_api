package s5_systemecommerciale_api.service;

import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.user.Users;
import s5_systemecommerciale_api.repository.user.UserRepository;

import java.util.Optional;

@Service
public class LoginServices {
    private final UserRepository userRepository;

    public LoginServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users login(Users users) throws Exception {
        Optional<Users> users1= userRepository.getUsersByEmailAndAndPassword(users.getEmail(), users.getPassword());
        if(users1.isPresent()){
            return users1.get();
        }
        throw new Exception("Username or password invalid");
    }
}
