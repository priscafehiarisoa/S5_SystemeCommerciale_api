package s5_systemecommerciale_api.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s5_systemecommerciale_api.model.user.Users;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> getUsersByEmailAndAndPassword(String email, String password);
}
