package s5_systemecommerciale_api.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s5_systemecommerciale_api.model.user.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
}
