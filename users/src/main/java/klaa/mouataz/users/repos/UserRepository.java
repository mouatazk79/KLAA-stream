package klaa.mouataz.users.repos;

import klaa.mouataz.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserById(UUID uuid);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String userName);
}
