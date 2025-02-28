package by.subscriptions.yemialyanava.managementsubscriptions.repository;

import by.subscriptions.yemialyanava.managementsubscriptions.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
