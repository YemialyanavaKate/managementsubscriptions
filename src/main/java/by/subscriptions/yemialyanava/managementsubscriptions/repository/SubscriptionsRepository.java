package by.subscriptions.yemialyanava.managementsubscriptions.repository;

import by.subscriptions.yemialyanava.managementsubscriptions.models.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Integer> {
    @Query(value = "SELECT * FROM SUBSCRIPTIONS S WHERE S.ID  IN " +
            "(SELECT SUBSCRIPTIONS_ID FROM USERS_SUBSCRIPTIONS WHERE USERS_ID =:id)", nativeQuery = true)
    Iterable<Subscriptions> findSubscriptionByUser (@Param("id") Integer id);
}
